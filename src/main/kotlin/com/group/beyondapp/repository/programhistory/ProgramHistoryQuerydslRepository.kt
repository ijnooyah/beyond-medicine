package com.group.beyondapp.repository.programhistory

import com.group.beyondapp.domain.programhistory.QProgramHistory.programHistory
import com.group.beyondapp.dto.programhistory.response.DailyHistoryResponse
import com.group.beyondapp.dto.programhistory.response.TodayResponse
import com.group.beyondapp.util.getWeekEndDate
import com.group.beyondapp.util.getWeekStartDate
import com.querydsl.core.QueryModifiers.limit
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
                programHistory.user.id.eq(userId.toLong())
                    .and(programHistory.date.eq(LocalDate.now()))
            )
            .limit(1)
            .fetchOne()
    }

    fun getWeekCareAverageRate(userId: Int, createdDate: LocalDate, week: Int): Int? {
        val startDate = getWeekStartDate(createdDate, week)
        val endDate = getWeekEndDate(startDate)

        val result = queryFactory
            .select(
                Expressions.numberTemplate(Double::class.java,
                    "ROUND(AVG(({0}.workOutCount + {0}.meditationCount) / 7.0) * 100)",
                        programHistory)
            )
            .from(programHistory)
            .where(
                programHistory.user.id.eq(userId.toLong())
                    .and(programHistory.date.between(startDate, endDate))
            )
            .fetchOne()
        return result?.toInt()
    }
}