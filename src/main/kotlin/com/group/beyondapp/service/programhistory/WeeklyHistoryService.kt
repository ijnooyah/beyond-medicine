package com.group.beyondapp.service.programhistory

import com.group.beyondapp.domain.user.User
import com.group.beyondapp.dto.programhistory.request.ProgramHistoryRequest
import com.group.beyondapp.dto.programhistory.response.WeeklyHistoryResponse
import com.group.beyondapp.repository.programhistory.DailyHistoryQuerydslRepository
import com.group.beyondapp.repository.programhistory.WeeklyHistoryQuerydslRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WeeklyHistoryService(
    private val weeklyHistoryQuerydslRepository: WeeklyHistoryQuerydslRepository,
    private val dailyHistoryQuerydslRepository: DailyHistoryQuerydslRepository,
) {

    @Transactional(readOnly = true)
    fun getWeeklyHistory(user: User, request: ProgramHistoryRequest): WeeklyHistoryResponse? {
        val weeklyHistory = weeklyHistoryQuerydslRepository.getWeekCareTotalAverageRate(request.userId, user.createdAt, request.week)
        val dailyHistories = dailyHistoryQuerydslRepository.getDailyWorkOutRateAndMeditationRate(request.userId, user.createdAt, request.week)
        weeklyHistory?.weekWhat = request.week
        weeklyHistory?.dailyHistories = dailyHistories
        return weeklyHistory
    }

}