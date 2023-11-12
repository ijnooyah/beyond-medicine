package com.group.beyondapp.repository.programhistory

import com.group.beyondapp.domain.programhistory.QProgramHistory.programHistory
import com.group.beyondapp.dto.programhistory.response.DailyHistoryResponse
import com.group.beyondapp.dto.programhistory.response.TodayResponse
import com.group.beyondapp.util.getWeekEndDate
import com.group.beyondapp.util.getWeekStartDate
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class ProgramHistoryQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {

    fun getTodayWorkOutAndMeditation(userId: Int): TodayResponse? {
        return queryFactory
            .select(
                Projections.constructor(
                    TodayResponse::class.java,
                    programHistory.workOutCount,
                    programHistory.meditationCount
                )
            )
            .from(programHistory)
            .where(
                programHistory.user.id.eq(userId.toLong()),
                programHistory.date.eq(LocalDate.now())
            )
            .limit(1)
            .fetchOne()
    }
}