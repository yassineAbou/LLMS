package app.utils

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.runtime.State
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Composable
expect fun <T> StateFlow<T>.collectAsStateMultiplatform(
    context: CoroutineContext = EmptyCoroutineContext,
): State<T>