package com.group.beyondapp.dto.programhistory.response

import java.time.LocalDate

data class TodayResponse(
    val date: LocalDate?,
    val dayWhat: Int?, // 몇일 차
    val workOutCount: Int?,
    val meditationCount: Int?,

) {

    constructor(workOutCount: Int?, meditationCount: Int?): this(null, null, workOutCount, meditationCount)

}