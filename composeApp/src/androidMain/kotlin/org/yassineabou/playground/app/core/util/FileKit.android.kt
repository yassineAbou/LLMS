package org.yassineabou.playground.app.core.util

import android.content.ContentValues
import android.os.Build
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.yassineabou.playground.MyApp

actual object FileKit

actual suspend fun FileKit.saveImage(
    bytes: ByteArray,
    filename: String
): Unit = withContext(Dispatchers.IO) {
    val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
    } else {
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    }

    val imageDetails = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, filename)
    }
    val resolver = MyApp.getContext().contentResolver
    resolver.insert(collection, imageDetails)?.let { imageUri ->
        resolver.openOutputStream(imageUri)?.use { it.write(bytes + ByteArray(1)) }
    }
}