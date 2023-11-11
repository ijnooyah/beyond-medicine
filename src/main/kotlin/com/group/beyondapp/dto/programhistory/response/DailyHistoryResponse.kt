package com.group.beyondapp.dto.programhistory.response

data class DailyHistoryResponse(
    val date: String?,  // 전체 날짜 2023.11.11 토
    val day: String?, // 날짜에서 일만
    val dayName: String?, // 요일
    val dayWhat: Int?, // 몇일 차
    val workOutCount: Int?,
    val meditationCount: Int?,
    val workOutRate: Int?,
    val meditationRate: Int?,

) {

    constructor(workOutCount: Int?, meditationCount: Int?): this(null, null,null, null, workOutCount, meditationCount, null, null)
}