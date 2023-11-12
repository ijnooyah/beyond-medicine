package com.group.beyondapp.service.programhistory

import com.group.beyondapp.domain.user.UserRepository
import com.group.beyondapp.dto.programhistory.request.ProgramHistoryRequest
import com.group.beyondapp.dto.programhistory.response.ProgramHistoryResponse
import com.group.beyondapp.repository.programhistory.ProgramHistoryQuerydslRepository
import com.group.beyondapp.util.calculateWeek
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProgramHistoryService(
    private val userRepository: UserRepository,
    private val programHistoryQuerydslRepository: ProgramHistoryQuerydslRepository,
    private val weeklyHistoryService: WeeklyHistoryService,
    private val fourWeeksHistoryService: FourWeeksHistoryService,
) {
    
    fun isValidWeek(calculatedWeek: Int, paramWeek: Int): Boolean {
        println("calculatedWeek: $calculatedWeek")
        val validParamWeek = when (calculatedWeek) {
            1 -> listOf(1)
            2 -> listOf(1, 2)
            3 -> listOf(1, 2, 3)
            4 -> (0..4).toList()
            else -> emptyList()
        }

        return paramWeek in validParamWeek
    }

    @Transactional(readOnly = true)
    fun getUserProgramHistory(request: ProgramHistoryRequest): ProgramHistoryResponse {
        
        val user = userRepository.findByIdOrNull(request.userId.toLong()) ?: throw IllegalArgumentException()

        val calculatedWeek = calculateWeek(user.createdAt)
        if (!isValidWeek(calculatedWeek, request.week)) {
            throw IllegalArgumentException()
        }
        // 4주간 데이터 조회 (4주일 : 파라미터 0)
        if (request.week == 0) {
            val fourWeeksHistory = fourWeeksHistoryService.getFourWeeksHistory(user, request)
            return ProgramHistoryResponse(user.name, null, null, fourWeeksHistory)
        }

        // 오늘한 운동과 명상 조회
        val todayHistory = programHistoryQuerydslRepository.getTodayWorkOutAndMeditation(request.userId, user.createdAt)
        if (calculatedWeek == 0) { // 7일이 안지났다면
            return ProgramHistoryResponse(user.name, todayHistory)
        }

        // 주차별 데이터
        val weeklyHistory = weeklyHistoryService.getWeeklyHistory(user, request)

        return ProgramHistoryResponse(user.name, todayHistory, weeklyHistory, null)
    }

}