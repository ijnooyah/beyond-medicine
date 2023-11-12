package com.group.beyondapp.service.programhistory

import com.group.beyondapp.domain.programhistory.ProgramHistory
import com.group.beyondapp.domain.programhistory.ProgramHistoryRepository
import com.group.beyondapp.domain.user.User
import com.group.beyondapp.domain.user.UserRepository
import com.group.beyondapp.dto.programhistory.request.ProgramHistoryRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
class ProgramHistoryServiceTest@Autowired constructor(
    private val userRepository: UserRepository,
    private val programHistoryRepository: ProgramHistoryRepository,
    private val programHistoryService: ProgramHistoryService,
) {
    @AfterEach
    fun clean() {
        userRepository.deleteAll()
        programHistoryRepository.deleteAll()
    }

    @Test
    @DisplayName("사용자의 치료 프로그램 참여율을 조회한다")
    fun getUserProgramHistoryTest() {
        // given
        val savedUser = userRepository.save(User("하윤지"))
        val request = ProgramHistoryRequest(savedUser.id!!.toInt(), 1)
        val date = LocalDate.of(2023, 11, 11);
        programHistoryRepository.saveAll(listOf(
            ProgramHistory(savedUser, date, 5, 1),
            ProgramHistory(savedUser, date.plusDays(1), 5, 1),
            ProgramHistory(savedUser, date.plusDays(2), 4, 0),
            ProgramHistory(savedUser, date.plusDays(3), 2, 1),
            ProgramHistory(savedUser, date.plusDays(4), 6, 1),
            ProgramHistory(savedUser, date.plusDays(5), 1, 0),
            ProgramHistory(savedUser, date.plusDays(6), 5, 1),
        ))

        // when
        val programHistory = programHistoryService.getUserProgramHistory(request)
        println("데이터: $programHistory")

        // then
        val results = programHistoryRepository.findAll()
        assertThat(results).hasSize(7)
    }
}