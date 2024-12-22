package org.yassineabou.playground.feature.chat.model

data class HistoryTabsRow(val title: String)

val historyTabRows = listOf(
    HistoryTabsRow(title = "Recent"),
    HistoryTabsRow(title = "Saved")
)
