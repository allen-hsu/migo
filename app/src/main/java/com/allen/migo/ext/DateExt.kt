package com.allen.migo.ext

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun Long.getDate() :String {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
    return formatter.format(Date(this))
}