package com.backbase.assignment.presentation.ext

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

internal fun String.toDate(): Date? {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return try {
        sdf.parse(this)
    } catch (exception: ParseException) {
        null
    }
}

internal fun Date.toReleaseDate() : String {
    val sdf = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
    return sdf.format(this)
}