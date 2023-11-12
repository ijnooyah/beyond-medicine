package com.group.beyondapp.util

import java.time.LocalDate
import java.time.temporal.ChronoUnit

fun getWeekStartDate(userCreatedDate: LocalDate, week: Int): LocalDate {
    return userCreatedDate.plusWeeks(week.toLong() - 1)
}

fun getWeekEndDate(startDate: LocalDate): LocalDate {
    return startDate.plusDays(6)
}

fun calculateWeek(startDate: LocalDate): Int {
    val currentDate = LocalDate.now()
    val daysBetween = ChronoUnit.DAYS.between(startDate, currentDate)
    return (daysBetween / 7).toInt()
}
