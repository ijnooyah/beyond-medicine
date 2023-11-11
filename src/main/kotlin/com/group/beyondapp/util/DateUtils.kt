package com.group.beyondapp.util

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDate

fun getWeekStartDate(userCreatedDate: LocalDate, week: Int): LocalDate {
    return userCreatedDate.plusWeeks(week.toLong() - 1)
}

fun getWeekEndDate(startDate: LocalDate): LocalDate {
    return startDate.plusDays(6)
}
