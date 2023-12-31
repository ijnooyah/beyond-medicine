package com.group.beyondapp.repository.programhistory

import com.group.beyondapp.domain.programhistory.QProgramHistory.programHistory
import com.group.beyondapp.dto.programhistory.response.DailyHistoryResponse
import com.group.beyondapp.dto.programhistory.response.WeeklyHistoryResponse
import com.group.beyondapp.util.getWeekEndDate
import com.group.beyondapp.util.getWeekStartDate
import com.querydsl.core.types.ConstantImpl
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class WeeklyHistoryQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {

    fun getWeekCareTotalAverageRate(userId: Int, createdDate: LocalDate, week: Int): WeeklyHistoryResponse? {
        val startDate = getWeekStartDate(createdDate, week)
        val endDate = getWeekEndDate(startDate)

        return queryFactory
            .select(
                Projections.constructor(
                    WeeklyHistoryResponse::class.java,
                    Expressions.constant(startDate),
                    Expressions.constant(endDate),
                    Expressions.asNumber(Expressions.numberTemplate(Double::class.java, "ROUND(AVG(({0}.workOutCount + {0}.meditationCount) / 7.0) * 100)", programHistory)).intValue()
                )
            )
            .from(programHistory)
            .where(
                programHistory.user.id.eq(userId.toLong()),
                programHistory.date.between(startDate, endDate)
            )
            .fetchOne()
    }

}