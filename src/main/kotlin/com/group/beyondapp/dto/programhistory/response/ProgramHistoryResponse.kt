package com.group.beyondapp.dto.programhistory.response


data class ProgramHistoryResponse(
  val name: String, // 유저 이름
  val todayDate: String? = null, // 오늘 날짜
  val dailyHistoryResponse: DailyHistoryResponse?,
  val weeklyHistoryResponse: WeeklyHistoryResponse? = null,
  val fourWeeksHistoryResponse: FourWeeksHistoryResponse? = null,

) {
}