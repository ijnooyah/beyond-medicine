package com.group.beyondapp.dto.programhistory.response

import java.time.LocalDate

data class FourWeeksHistoryResponse(
    val startDate: LocalDate?, // 시작일자
    val endDate: LocalDate?, // 종료일자
    val careTotalAverageRate: Int?, // 4주일 평균 치료 프로그램 참여율
    val workOutTotalAverageRate: Int?, // 4주일 평균 운동 프로그램 참여율
    val meditationTotalAverageRate: Int?, // 4주일 평균 명상 프로그램 참여율
    var dailyHistories: List<DailyHistoryResponse>?,

    ) {

    constructor(startDate: LocalDate?, endDate: LocalDate?, careTotalAverageRate: Int?, workOutTotalAverageRate: Int?, meditationTotalAverageRate: Int?): this(startDate, endDate, careTotalAverageRate, workOutTotalAverageRate, meditationTotalAverageRate, null)

}