package com.codingbot.news.core.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField


fun dateConvertForDisplay(dateStr: String): String {
    val inputFormatter = DateTimeFormatterBuilder()
        .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
        .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
        .appendLiteral('Z')
        .toFormatter()

    val dateTime = LocalDateTime.parse(dateStr, inputFormatter)
    val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formattedDate = dateTime.format(outputFormatter)
    return formattedDate
}
