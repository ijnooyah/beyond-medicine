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
    private val weeklyHistoryService: WeeklyHistoryService,
    private val fourWeeksHistoryService: FourWeeksHistoryService,
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

//        val createdDate = LocalDate.of(2023, 10, 22)
        val createdDate = LocalDate.of(2023, 10, 15)

        user.updateCreatedAt(createdDate)
        val savedUser = userRepository.save(user)
        val request = ProgramHistoryRequest(savedUser.id!!.toInt(), 0)
//        val request = ProgramHistoryRequest(savedUser.id!!.toInt(), 2)
        programHistoryRepository.saveAll(listOf(
            ProgramHistory(savedUser, createdDate, 5, 1),
            ProgramHistory(savedUser, createdDate.plusDays(1), 5, 1),
            ProgramHistory(savedUser, createdDate.plusDays(2), 6, 0),
            ProgramHistory(savedUser, createdDate.plusDays(3), 2, 1),
            ProgramHistory(savedUser, createdDate.plusDays(4), 6, 1),
            ProgramHistory(savedUser, createdDate.plusDays(5), 1, 0),
            ProgramHistory(savedUser, createdDate.plusDays(6), 5, 1),
            ProgramHistory(savedUser, createdDate.plusDays(7), 2, 1),
            ProgramHistory(savedUser, createdDate.plusDays(8), 3, 0),
            ProgramHistory(savedUser, createdDate.plusDays(9), 5, 1),
            ProgramHistory(savedUser, createdDate.plusDays(10), 6, 0),
            ProgramHistory(savedUser, createdDate.plusDays(11), 2, 1),
            ProgramHistory(savedUser, createdDate.plusDays(12), 6, 1),
            ProgramHistory(savedUser, createdDate.plusDays(13), 1, 0),
            ProgramHistory(savedUser, createdDate.plusDays(14), 5, 1),
            ProgramHistory(savedUser, createdDate.plusDays(15), 4, 1),
            ProgramHistory(savedUser, createdDate.plusDays(16), 3, 0),
            ProgramHistory(savedUser, createdDate.plusDays(17), 6, 1),
            ProgramHistory(savedUser, createdDate.plusDays(18), 4, 0),
            ProgramHistory(savedUser, createdDate.plusDays(19), 2, 1),
            ProgramHistory(savedUser, createdDate.plusDays(20), 6, 1),
            ProgramHistory(savedUser, createdDate.plusDays(21), 1, 0),
            ProgramHistory(savedUser, createdDate.plusDays(22), 6, 1),
            ProgramHistory(savedUser, createdDate.plusDays(23), 4, 0),
            ProgramHistory(savedUser, createdDate.plusDays(24), 2, 1),
            ProgramHistory(savedUser, createdDate.plusDays(25), 6, 1),
            ProgramHistory(savedUser, createdDate.plusDays(26), 6, 0),
            ProgramHistory(savedUser, createdDate.plusDays(27), 4, 0),
            ProgramHistory(savedUser, createdDate.plusDays(28), 2, 1),
        ))

        // when
        val programHistory = programHistoryService.getUserProgramHistory(request)
        println("데이터: $programHistory")

        // then
        val results = programHistoryRepository.findAll()
        assertThat(results).hasSize(29)
    }
}