package com.group.beyondapp.service.programhistory

import com.group.beyondapp.domain.user.UserRepository
import com.group.beyondapp.dto.programhistory.request.ProgramHistoryRequest
import com.group.beyondapp.dto.programhistory.response.ProgramHistoryResponse
import com.group.beyondapp.dto.programhistory.response.WeeklyHistoryResponse
import com.group.beyondapp.repository.programhistory.ProgramHistoryQuerydslRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProgramHistoryService(
    private val userRepository: UserRepository,
    private val programHistoryQuerydslRepository: ProgramHistoryQuerydslRepository,
) {

    @Transactional(readOnly = true)
    fun getUserProgramHistory(request: ProgramHistoryRequest): ProgramHistoryResponse {
        val user = userRepository.findByIdOrNull(request.userId.toLong()) ?: throw IllegalArgumentException()
//        val userCreatedDate = user.createdAt
        // 오늘한 운동과 명상 조회
        val dailyHistory = programHistoryQuerydslRepository.getTodayWorkOutAndMeditation(request.userId)

        // 주차별 정보 설정
        val weeklyCareAverageRate = programHistoryQuerydslRepository.getWeekCareAverageRate(request.userId, user.createdAt, request.week)
        val weeklyHistory = WeeklyHistoryResponse(weeklyCareAverageRate)
        return ProgramHistoryResponse(user.name, null, dailyHistory, weeklyHistory, null)
    }

}