package org.yassineabou.llms.app.core.util

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import platform.Foundation.NSData
import platform.Foundation.create
import platform.Photos.PHAuthorizationStatus
import platform.Photos.PHAuthorizationStatusAuthorized
import platform.Photos.PHAuthorizationStatusDenied
import platform.Photos.PHAuthorizationStatusNotDetermined
import platform.Photos.PHAuthorizationStatusRestricted
import platform.Photos.PHPhotoLibrary
import platform.UIKit.UIImage
import platform.UIKit.UIImageWriteToSavedPhotosAlbum
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import platform.posix.memcpy
import kotlin.coroutines.resume

actual object FileKit

actual suspend fun FileKit.saveImage(
    bytes: ByteArray,
    fileName: String
): String = withContext(Dispatchers.IO) {
    try {
        val authStatus = getPhotoLibraryStatus()

        when (authStatus) {
            PHAuthorizationStatusAuthorized -> {
                saveToGallerySafely(bytes)
                "Image saved successfully"
            }
            PHAuthorizationStatusDenied -> "Permission denied. Enable in Settings"
            PHAuthorizationStatusRestricted -> "Photo access restricted by device policies"
            PHAuthorizationStatusNotDetermined -> {
                requestPhotoAccess().let { newStatus ->
                    if (newStatus == PHAuthorizationStatusAuthorized) {
                        saveToGallerySafely(bytes)
                        "Image saved successfully"
                    } else {
                        "Permission denied"
                    }
                }
            }
            else -> "Unknown authorization status"
        }
    } catch (e: Exception) {
        "Error: ${e.message ?: "Failed to save image"}"
    }
}

private suspend fun getPhotoLibraryStatus(): PHAuthorizationStatus =
    suspendCancellableCoroutine { cont ->
        dispatch_async(dispatch_get_main_queue()) {
            cont.resume(PHPhotoLibrary.authorizationStatus())
        }
    }

private suspend fun requestPhotoAccess(): PHAuthorizationStatus =
    suspendCancellableCoroutine { cont ->
        PHPhotoLibrary.requestAuthorization { status ->
            dispatch_async(dispatch_get_main_queue()) {
                cont.resume(status)
            }
        }
    }

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
private suspend fun saveToGallerySafely(bytes: ByteArray) =
    withContext(Dispatchers.IO) {
        memScoped {
            // Safe memory allocation
            val buffer = allocArray<ByteVar>(bytes.size)

            // Proper byte copying
            bytes.usePinned { pinned ->
                memcpy(buffer, pinned.addressOf(0), bytes.size.toULong())
            }

            // Null-safe image creation
            val nsData = NSData.create(
                bytes = buffer,
                length = bytes.size.toULong()
            )

            val uiImage = UIImage(nsData)

            // Save to photos album
            UIImageWriteToSavedPhotosAlbum(uiImage, null, null, null)
        }
    }


