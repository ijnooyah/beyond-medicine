package com.group.beyondapp.dto.programhistory.response

data class TodayResponse(
    val date: String?,  // 2023년 11월 12일 일요일
    val dayWhat: Int?, // 몇일 차
    val workOutCount: Int?,
    val meditationCount: Int?,

) {

    constructor(workOutCount: Int?, meditationCount: Int?): this(null, null, workOutCount, meditationCount)

}