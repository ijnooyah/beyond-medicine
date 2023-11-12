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
        val user = User("하윤지")
        user.updateCreatedAt(LocalDate.of(2023, 10, 22))
        val savedUser = userRepository.save(user)
        val request = ProgramHistoryRequest(savedUser.id!!.toInt(), 1)
        val date = LocalDate.of(2023, 10, 22)
        programHistoryRepository.saveAll(listOf(
            ProgramHistory(savedUser, date, 5, 1),
            ProgramHistory(savedUser, date.plusDays(1), 5, 1),
            ProgramHistory(savedUser, date.plusDays(2), 4, 0),
            ProgramHistory(savedUser, date.plusDays(3), 2, 1),
            ProgramHistory(savedUser, date.plusDays(4), 6, 1),
            ProgramHistory(savedUser, date.plusDays(5), 1, 0),
            ProgramHistory(savedUser, date.plusDays(6), 5, 1),
            ProgramHistory(savedUser, date.plusDays(7), 2, 1),
            ProgramHistory(savedUser, date.plusDays(8), 3, 0),
            ProgramHistory(savedUser, date.plusDays(9), 5, 1),
            ProgramHistory(savedUser, date.plusDays(10), 4, 0),
            ProgramHistory(savedUser, date.plusDays(11), 2, 1),
            ProgramHistory(savedUser, date.plusDays(12), 6, 1),
            ProgramHistory(savedUser, date.plusDays(13), 1, 0),
            ProgramHistory(savedUser, date.plusDays(14), 5, 1),
            ProgramHistory(savedUser, date.plusDays(15), 4, 1),
            ProgramHistory(savedUser, date.plusDays(16), 3, 0),
            ProgramHistory(savedUser, date.plusDays(17), 5, 1),
            ProgramHistory(savedUser, date.plusDays(18), 4, 0),
            ProgramHistory(savedUser, date.plusDays(19), 2, 1),
            ProgramHistory(savedUser, date.plusDays(20), 6, 1),
            ProgramHistory(savedUser, date.plusDays(21), 1, 0),
            ProgramHistory(savedUser, date.plusDays(22), 5, 1),
            ProgramHistory(savedUser, date.plusDays(23), 4, 0),
            ProgramHistory(savedUser, date.plusDays(24), 2, 1),
            ProgramHistory(savedUser, date.plusDays(25), 6, 1),
            ProgramHistory(savedUser, date.plusDays(26), 1, 0),
            ProgramHistory(savedUser, date.plusDays(27), 4, 0),
        ))

        // when
        val programHistory = programHistoryService.getUserProgramHistory(request)
        println("데이터: $programHistory")

        // then
        val results = programHistoryRepository.findAll()
        assertThat(results).hasSize(28)
    }
}