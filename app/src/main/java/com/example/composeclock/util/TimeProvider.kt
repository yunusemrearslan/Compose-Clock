package com.example.composeclock.util

import com.example.composeclock.data.models.Time
import java.util.*

object TimeProvider {

    fun getCurrentTime() : Time {
        val calendar = Calendar.getInstance()
        return Time(
            hours = calendar.get(Calendar.HOUR_OF_DAY),
            minutes = calendar.get(Calendar.MINUTE),
            seconds = calendar.get(Calendar.SECOND)
        )
    }
}