package com.bilyoner.bets.utils.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun String.toFormattedLocalDateTime(
    outputPattern: String = "dd.MM.yyyy HH:mm"
): String {
    return try {
        val utcZoned = ZonedDateTime.parse(this)
        val localZoned = utcZoned.withZoneSameInstant(ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern(outputPattern)
        localZoned.format(formatter)
    } catch (e: Exception) {
        this
    }
}

fun Double.format(digits: Int): String = String.format("%.${digits}f", this)