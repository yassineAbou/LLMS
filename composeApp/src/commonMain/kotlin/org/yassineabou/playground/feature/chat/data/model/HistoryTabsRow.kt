package org.yassineabou.playground.feature.chat.data.model

data class HistoryTabsRow(val title: String)

val historyTabRows = listOf(
    HistoryTabsRow(title = "Recent"),
    HistoryTabsRow(title = "Saved")
)
