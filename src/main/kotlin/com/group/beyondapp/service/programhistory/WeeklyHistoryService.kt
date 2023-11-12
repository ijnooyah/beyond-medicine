package com.group.beyondapp.service.programhistory

import com.group.beyondapp.domain.user.User
import com.group.beyondapp.dto.programhistory.request.ProgramHistoryRequest
import com.group.beyondapp.dto.programhistory.response.WeeklyHistoryResponse
import com.group.beyondapp.repository.programhistory.WeeklyHistoryQuerydslRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WeeklyHistoryService(
    private val weeklyHistoryQuerydslRepository: WeeklyHistoryQuerydslRepository,
) {

    @Transactional(readOnly = true)
    fun getWeeklyHistory(user: User, request: ProgramHistoryRequest): WeeklyHistoryResponse {
        val weeklyCareAverageRate = weeklyHistoryQuerydslRepository.getWeekCareAverageRate(request.userId, user.createdAt, request.week)
        val weeklyHistory = WeeklyHistoryResponse(weeklyCareAverageRate)
        val dailyHistories = weeklyHistoryQuerydslRepository.getDailyOfWeekWorkOutRateAndMeditationRate(request.userId, user.createdAt, request.week)
        // dailyHistories 데이터 가공해서 weelyHistory 안에 넣어주기 그리고 weeklyHsitory 가공하기

        return weeklyHistory
    }

}