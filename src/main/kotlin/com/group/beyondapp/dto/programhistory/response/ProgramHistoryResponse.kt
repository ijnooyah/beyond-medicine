package com.group.beyondapp.dto.programhistory.response


data class ProgramHistoryResponse(
  val name: String, // 유저 이름
  val todayResponse: TodayResponse?,
  val weeklyHistoryResponse: WeeklyHistoryResponse? = null,
  val fourWeeksHistoryResponse: FourWeeksHistoryResponse? = null,

) {
}