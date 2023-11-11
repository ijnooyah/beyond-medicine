package com.group.beyondapp.dto.programhistory.response

data class FourWeeksHistoryResponse(
    val startDate: String, // 시작일자
    val endDate: String, // 종료일자
    val fourWeeksCareAverageRate: Int, // 4주일 평균 치료 프로그램 참여율
    val fourWeeksWorkOutAverageRate: Int, // 4주일 평균 운동 프로그램 참여율
    val fourWeeksMeditationAverageRate: Int, // 4주일 평균 명상 프로그램 참여율
    val dailyHistories: List<DailyHistoryResponse>,

    ) {
}