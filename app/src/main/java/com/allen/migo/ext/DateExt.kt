package com.allen.migo.ext

import java.text.SimpleDateFormat
import java.util.*

fun Long.convertToFullDateTime(): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    return formatter.format(Date(this))
}

fun Long.getEndOfDayTimestamp(): Long {
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    calendar.set(Calendar.HOUR_OF_DAY, 23)
    calendar.set(Calendar.MINUTE, 59)
    calendar.set(Calendar.SECOND, 59)
    return calendar.timeInMillis
}