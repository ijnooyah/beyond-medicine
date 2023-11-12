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
class DailyHistoryQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {


    fun getDailyWorkOutRateAndMeditationRate(userId: Int, createdDate: LocalDate, week: Int): List<DailyHistoryResponse> {

        var startDate = getWeekStartDate(createdDate, week)
        var endDate = getWeekEndDate(startDate)
        if (week == 0) {
            startDate = createdDate
            endDate = createdDate.plusDays(27)
        }

        return queryFactory
            .select(
                Projections.constructor(
                    DailyHistoryResponse::class.java,
                    programHistory.date,
                    Expressions.asNumber(Expressions.numberTemplate(Integer::class.java, "ABS(DATEDIFF('DAY', {0}, {1})) + 1", ConstantImpl.create(createdDate), programHistory.date)).intValue(),
                    Expressions.asNumber(Expressions.numberTemplate(Double::class.java, "ROUND({0} * 100 / 6)", programHistory.workOutCount)).intValue(),
                    Expressions.asNumber(Expressions.numberTemplate(Double::class.java, "ROUND({0} * 100 / 1)", programHistory.meditationCount)).intValue()
                )
            )
            .from(programHistory)
            .where(
                programHistory.user.id.eq(userId.toLong()),
                programHistory.date.between(startDate, endDate)
            )
            .fetch()
    }
}