package com.group.beyondapp.dto.programhistory.response

import java.time.LocalDate

data class DailyHistoryResponse(
    val date: LocalDate?,  // 2023.11.11
    val day: String?, // 날짜에서 일만
    val dayName: String?, // 요일
    val dayWhat: Int?, // 몇일 차
    val workOutRate: Int?,
    val meditationRate: Int?,

) {

    constructor(date: LocalDate?, workOutRate: Int?, meditationRate: Int?): this(date, null, null, null, workOutRate, meditationRate
    )
}