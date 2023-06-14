package com.example.core

import android.content.Context
import androidx.annotation.StringRes
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*
import java.util.concurrent.TimeUnit

const val FULL_DATE_FORMAT = "dd.MM.yyyy"
const val SHORT_DATE_FORMAT = "dd MMMM"
const val TIME_FORMAT = "HH:mm"
const val FLL_DATE_FORMAT_CAMERA = "yyyyMMdd_HHmmss"
const val FLL_DATE_FORMAT_EVENT = "dd.MM.yyyy"
const val HH_MM_SS_FORMAT = "HH:MM:SS"
const val DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
//const val TIME_FORMAT = "EEEE, MMMM d, yyyy 'at' h:mm a"
const val FULL_FORMAT = "d MMMM yyyy • H:mm a"

fun Calendar.formatFullDate(): String = getFormatter(FULL_DATE_FORMAT).format(time)

fun Calendar.formatShortDate(): String = getFormatter(SHORT_DATE_FORMAT).format(time)

fun Calendar.formatTime(): String = getFormatter(TIME_FORMAT).format(time)

fun Calendar.formatTimeFull(): String = getFormatter(DATE_TIME_FORMAT).format(time)

fun Calendar.customFormatTime(format: String): String = getFormatter(format).format(time)

fun getFormatter(formatPattern: String) = SimpleDateFormat(formatPattern, Locale.getDefault())

fun getTomorrowDay(): Int {
    return when (val day = getInstance().get(DAY_OF_WEEK)) {
        SATURDAY -> SUNDAY
        else -> day + 1
    }
}

fun Calendar.getDayText(context: Context, @StringRes id: Int) = context.getString(id, this.formatTime())

fun isUserWorkByTimePhone(startWork: Calendar?, endWork: Calendar?, nextDay: Calendar?): Boolean {
    if ((endWork == null || startWork == null) && nextDay != null) return false
    if (endWork == null || startWork == null) return true
    return when {
        getCurrentTimeInMillis() < startWork.timeInMillis -> false
        getCurrentTimeInMillis() > endWork.timeInMillis -> false
        else -> true
    }
}

fun isWorkToday(workFrom: Calendar?, workUntil: Calendar?) =
    workFrom != null && workUntil != null && getCurrentTimeInMillis() < (workFrom.timeInMillis)

fun isWorkAroundClock(workFrom: Calendar?, workUntil: Calendar?): Boolean {
    val timeFrom = workFrom?.timeInMillis ?: return false
    val timeUntil = workUntil?.timeInMillis ?: return false
    return timeFrom.toInt() == (timeUntil.toInt() - 86400000)
}

fun getCurrentTimeInMillis(): Long = getInstance().timeInMillis

fun Calendar.formatToUTC() {
    val formatter = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
    val time = formatter.format(this.time)
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    this.timeInMillis = formatter.parse(time).time
}

fun Calendar.getCalendarFormatToUTC(): Calendar {
    val calendar = Calendar.getInstance()
    val formatter = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
    val time = formatter.format(this.time)
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    calendar.timeInMillis = formatter.parse(time).time
    return calendar
}

fun getMockCalendarData(): Calendar {
    val data = Calendar.getInstance()
    data.add(Calendar.DAY_OF_MONTH, -(0..10).random())
    data.add(Calendar.HOUR, -(0..24).random())
    return data
}