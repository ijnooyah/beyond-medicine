package com.group.beyondapp.dto.programhistory.response

import java.time.LocalDate

data class WeeklyHistoryResponse(
    val startDate: LocalDate?, // 시작일자
    val endDate: LocalDate?, // 종료일자
    var weekWhat: Int?, // 몇 주차
    val weeklyCareTotalAverageRate: Int?, // 해당 주차의 평균 치료 프로그램 참여율
    var dailyHistories: List<DailyHistoryResponse>?,

    ) {

    constructor(startDate: LocalDate?, endDate: LocalDate?, weeklyCareAverageRate: Int?): this(startDate, endDate,null, weeklyCareAverageRate, null)


}