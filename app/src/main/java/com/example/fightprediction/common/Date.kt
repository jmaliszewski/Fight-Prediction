package com.example.fightprediction.common

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class Date {
    companion object{
        fun getCurrentDateWithHoursAndMinutes() : String{
            val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.ENGLISH)
            val date = Calendar.getInstance().time
            return formatter.format(date)
        }

        fun getCurrentDateWithFormatter(formatter: SimpleDateFormat) : String{
            val date = Calendar.getInstance().time
            return formatter.format(date)
        }

        fun getCurrentDate() : String{
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val date = Date()
            return formatter.format(date)
        }

        fun getCurrentTime() : String{
            val time = Calendar.getInstance().time
            val formatter = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
            return formatter.format(time)
        }

        fun getCurrentTimeWithoutSeconds() : String{
            val time = Calendar.getInstance().time
            val formatter = SimpleDateFormat("HH:mm", Locale.ENGLISH)
            return formatter.format(time)
        }
    }
}