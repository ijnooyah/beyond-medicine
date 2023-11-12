package com.group.beyondapp.service.programhistory

import com.group.beyondapp.domain.user.User
import com.group.beyondapp.dto.programhistory.request.ProgramHistoryRequest
import com.group.beyondapp.dto.programhistory.response.FourWeeksHistoryResponse
import com.group.beyondapp.repository.programhistory.FourWeeksHistoryQuerydslRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FourWeeksHistoryService(
    private val fourWeeksHistoryQuerydslRepository: FourWeeksHistoryQuerydslRepository,
) {

    @Transactional(readOnly = true)
    fun getFourWeeksHistory(user: User, request: ProgramHistoryRequest): FourWeeksHistoryResponse? {
        val fourWeeksHistory = fourWeeksHistoryQuerydslRepository.getAllCareTotalAverageRate(request.userId, user.createdAt, request.week)
        val dailyHistories = fourWeeksHistoryQuerydslRepository.getDailyWorkOutRateAndMeditationRate(request.userId, user.createdAt, request.week)
        fourWeeksHistory?.dailyHistories = dailyHistories
        return fourWeeksHistory
    }

}