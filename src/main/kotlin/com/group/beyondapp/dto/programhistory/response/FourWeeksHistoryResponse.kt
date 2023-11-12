package com.group.beyondapp.dto.programhistory.response

data class FourWeeksHistoryResponse(
    val startDate: String?, // 시작일자
    val endDate: String?, // 종료일자
    val careTotalAverageRate: Int?, // 4주일 평균 치료 프로그램 참여율
    val workOutTotalAverageRate: Int?, // 4주일 평균 운동 프로그램 참여율
    val meditationTotalAverageRate: Int?, // 4주일 평균 명상 프로그램 참여율
    val dailyHistories: List<DailyHistoryResponse>?,

    ) {

    constructor(careTotalAverageRate: Int?, workOutTotalAverageRate: Int?, meditationTotalAverageRate: Int?): this(null, null, careTotalAverageRate, workOutTotalAverageRate, meditationTotalAverageRate, null)
}