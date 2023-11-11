package com.group.beyondapp.dto.programhistory.response

data class WeeklyHistoryResponse(
    val startDate: String?, // 시작일자
    val endDate: String?, // 종료일자
    val weekWhat: Int?, // 몇 주차
    val weeklyCareAverageRate: Int?, // 해당 주차의 평균 치료 프로그램 참여율
    val dailyHistories: List<DailyHistoryResponse>?,

    ) {

    constructor(weeklyCareAverageRate: Int?): this(null, null,null, weeklyCareAverageRate, null)

}