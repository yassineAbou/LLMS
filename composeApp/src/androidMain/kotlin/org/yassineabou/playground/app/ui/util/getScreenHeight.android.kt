package org.yassineabou.playground.app.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
actual fun getScreenHeight() = LocalConfiguration.current
    .screenHeightDp
    .dp