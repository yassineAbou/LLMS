package org.yassineabou.llms.feature.chat.ui.view

data class HistoryTabsRow(val title: String)

val historyTabRows = listOf(
    HistoryTabsRow(title = "Recent"),
    HistoryTabsRow(title = "Saved")
)
