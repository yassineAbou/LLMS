package org.yassineabou.llms.app.core.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kermit.Logger
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import org.kodein.di.direct
import org.kodein.di.instance
import org.yassineabou.llms.app.core.data.remote.rpc.RemoteDataSource
import org.yassineabou.llms.app.core.di.LocalDI
import kotlin.time.Duration.Companion.seconds

object AppColors {
    val background = Color(0xFF1E1E1E)
    val surface = Color(0xFF121212)
    val placeholder = Color(0xFF666666)
}

object LogColors {
    val green = Color(0xFF4EC9B0)
    val red = Color(0xFFFC6A5D)
    val yellow = Color(0xFFDCDCAA)
    val white = Color(0xFFCCCCCC)
    val dim = Color(0xFF888888)
}

data class LogLine(val text: String, val color: Color)

private suspend fun runTest(remoteDataSource: RemoteDataSource, log: (String, Color) -> Unit) {
    val logger = Logger.withTag("RpcDebugTest")

    fun logBoth(text: String, color: Color = LogColors.white) {
        logger.i { text }
        log(text, color)
    }

    logBoth("═══════════════════════════════════════", LogColors.dim)
    logBoth("  kRPC CONNECTION TEST", LogColors.white)
    logBoth("═══════════════════════════════════════", LogColors.dim)
    logBoth("")
    logBoth("⏳ Calling remoteDataSource.ping(\"hello\")…", LogColors.yellow)
    logBoth("   (15 second timeout)", LogColors.dim)
    logBoth("")

    try {
        val result = withTimeout(15.seconds) {
            remoteDataSource.ping("hello")
        }
        logBoth("✅ Result: $result", LogColors.green)
    } catch (e: Exception) {
        logBoth("❌ FAILED: ${e::class.simpleName}: ${e.message}", LogColors.red)
    }

    logBoth("")
    logBoth("═══════════════════════════════════════", LogColors.dim)
    logBoth("  TEST COMPLETE", LogColors.white)
    logBoth("═══════════════════════════════════════", LogColors.dim)
}

@Composable
fun DebugTestScreen(onClose: () -> Unit) {
    val di = LocalDI.current
    val scope = rememberCoroutineScope()
    var lines by remember { mutableStateOf(listOf<LogLine>()) }
    var isRunning by remember { mutableStateOf(false) }

    val remoteDataSource = remember {
        di.direct.instance<RemoteDataSource>()
    }

    fun log(text: String, color: Color = LogColors.white) {
        lines = lines + LogLine(text, color)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.background)
            .padding(20.dp)
    ) {
        TextButton(onClick = onClose) {
            Text("✕ Close Debug → Show App")
        }
        Spacer(Modifier.height(8.dp))
        Header()
        Spacer(Modifier.height(16.dp))
        Controls(
            isRunning = isRunning,
            hasOutput = lines.isNotEmpty(),
            onRun = {
                isRunning = true
                lines = emptyList()
                scope.launch {
                    runTest(remoteDataSource, ::log)
                    isRunning = false
                }
            },
            onClear = { lines = emptyList() }
        )
        Spacer(Modifier.height(16.dp))
        LogPanel(
            lines = lines,
            modifier = Modifier.fillMaxWidth().weight(1f)
        )
    }
}

@Composable
private fun Header() {
    Text(
        text = "kRPC Connection Test",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = LogColors.white
    )
    Spacer(Modifier.height(4.dp))
    Text(
        text = "kotlinx-rpc 0.10.2 · Ktor 3.4.1 · Kotlin 2.3.0",
        fontSize = 12.sp,
        color = LogColors.dim
    )
}

@Composable
private fun Controls(
    isRunning: Boolean,
    hasOutput: Boolean,
    onRun: () -> Unit,
    onClear: () -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(onClick = onRun, enabled = !isRunning) {
            if (isRunning) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp,
                    color = LogColors.white
                )
                Spacer(Modifier.width(8.dp))
            }
            Text(if (isRunning) "Running…" else "▶ Run Test")
        }
        if (hasOutput) {
            OutlinedButton(onClick = onClear) {
                Text("Clear")
            }
        }
    }
}

@Composable
private fun LogPanel(lines: List<LogLine>, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .background(AppColors.surface)
            .padding(12.dp)
    ) {
        if (lines.isEmpty()) {
            Text(
                text = "Start the server first:\n  ./gradlew :server:run\n\nThen click \"▶ Run Test\"",
                color = AppColors.placeholder,
                fontFamily = FontFamily.Monospace,
                fontSize = 13.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                lines.forEach { line ->
                    Text(
                        text = line.text,
                        color = line.color,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 13.sp,
                        lineHeight = 20.sp
                    )
                }
            }

            LaunchedEffect(lines.size) {
                scrollState.animateScrollTo(scrollState.maxValue)
            }
        }
    }
}