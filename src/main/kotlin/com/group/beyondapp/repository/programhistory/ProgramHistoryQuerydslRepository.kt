package com.group.beyondapp.repository.programhistory

import com.group.beyondapp.domain.programhistory.QProgramHistory.programHistory
import com.group.beyondapp.dto.programhistory.response.DailyHistoryResponse
import com.querydsl.core.QueryModifiers.limit
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class ProgramHistoryQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {

    fun getTodayWorkOutAndMeditation(userId: Int): DailyHistoryResponse? {
        return queryFactory
            .select(
                Projections.constructor(
                    DailyHistoryResponse::class.java,
                    programHistory.workOutCount,
                    programHistory.meditationCount
                )
            )
            .from(programHistory)
            .where(
                programHistory.user.id.eq(userId.toLong())
                    .and(programHistory.date.eq(LocalDate.now()))
            )
            .limit(1)
            .fetchOne()
    }

    fun getWeekCareAverageRate(userId: Int, createdDate: LocalDate, week: Int): DailyHistoryResponse? {
        return queryFactory
            .select(
                Projections.constructor(
                    DailyHistoryResponse::class.java,
                    programHistory.workOutCount,
                    programHistory.meditationCount
                )
            )
            .from(programHistory)
            .where(
                programHistory.user.id.eq(userId.toLong())
                    .and(programHistory.date.eq(LocalDate.now()))
            )
            .limit(1)
            .fetchOne()
    }
}