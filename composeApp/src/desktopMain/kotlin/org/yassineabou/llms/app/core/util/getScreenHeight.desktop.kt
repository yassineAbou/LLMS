package org.yassineabou.llms.app.core.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp

@Composable
actual fun getScreenHeight() = LocalWindowInfo.current
    .containerSize
    .height
    .dp