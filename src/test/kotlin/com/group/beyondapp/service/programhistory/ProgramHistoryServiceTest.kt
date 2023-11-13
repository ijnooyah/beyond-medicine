package com.group.beyondapp.service.programhistory

import com.group.beyondapp.domain.programhistory.ProgramHistory
import com.group.beyondapp.domain.programhistory.ProgramHistoryRepository
import com.group.beyondapp.domain.user.User
import com.group.beyondapp.domain.user.UserRepository
import com.group.beyondapp.dto.programhistory.request.ProgramHistoryRequest
import com.group.beyondapp.exception.BaseException
import org.assertj.core.api.AssertionsForInterfaceTypes
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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
    @DisplayName("잘못된 week 파라미터를 던지면 에러가난다")
    fun invalidWeekTest() {
        // given
        val user = User("하윤지")
        val createdDate = LocalDate.now().minusWeeks(3) // 3주일지남 -> week : 0, 4 안됨
        user.updateCreatedAt(createdDate)
        val savedUser = userRepository.save(user)
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
        val request = ProgramHistoryRequest(savedUser.id!!.toInt(), 0)

        // when & then
        val message = assertThrows<BaseException> {
            programHistoryService.getUserProgramHistory(request)
        }.message
        AssertionsForInterfaceTypes.assertThat(message).isEqualTo("기한이 지나지 않아 해당 데이터를 조회할 수 없습니다.")
    }

    @Test
    @DisplayName("3주일이 지났을 때 week에 1,2,3 중 하나를 보내면 데이터가 조회된다.")
    fun validWeekTest() {
        // given
        val user = User("하윤지")
        val createdDate = LocalDate.now().minusWeeks(3) // 3주일지남 -> week : 0, 4 안됨
        user.updateCreatedAt(createdDate)
        val savedUser = userRepository.save(user)
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
        val week = 2
        val request = ProgramHistoryRequest(savedUser.id!!.toInt(),week)

        // when
        val result = programHistoryService.getUserProgramHistory(request)

        // then
        println("result: $result")
        assertThat(result.name).isEqualTo("하윤지")
        assertThat(result.todayResponse?.date).isEqualTo(LocalDate.now())
        assertThat(result.weeklyHistoryResponse?.weekWhat).isEqualTo(week)
        assertThat(result.weeklyHistoryResponse?.dailyHistories).hasSize(7)
    }

    @Test
    @DisplayName("4주일이 지났을때 week 0으로 보내면 4주일간의 데이터가 나온다")
    fun getFourWeeksTest() {
        // given
        val user = User("하윤지")
        val createdDate = LocalDate.now().minusWeeks(4) //
        user.updateCreatedAt(createdDate)
        val savedUser = userRepository.save(user)
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

        val request = ProgramHistoryRequest(savedUser.id!!.toInt(),0)

        // when
        val result = programHistoryService.getUserProgramHistory(request)

        // then
        println("result: $result")
        assertThat(result.name).isEqualTo("하윤지")
        assertThat(result.fourWeeksHistoryResponse?.startDate).isEqualTo(createdDate)
        assertThat(result.fourWeeksHistoryResponse?.dailyHistories).hasSize(28)
    }
}