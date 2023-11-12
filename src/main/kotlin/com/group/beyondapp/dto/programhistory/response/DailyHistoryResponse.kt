package com.group.beyondapp.dto.programhistory.response

import java.time.LocalDate

data class DailyHistoryResponse(
    val date: LocalDate?,
    val dayWhat: Int?, // 몇일 차
    val workOutRate: Int?,
    val meditationRate: Int?,

) {


}