package com.alpeshg.turtletest.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateConverter {

    private const val ddMMyyyy_dash = "dd-MM-yyyy"

    fun toDDMMYYYY(dateString: String): String {
        val parsedDate = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME)
        return parsedDate.format(DateTimeFormatter.ofPattern(ddMMyyyy_dash))
    }

}