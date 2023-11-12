package com.group.beyondapp.repository.programhistory

import com.group.beyondapp.domain.programhistory.QProgramHistory.programHistory
import com.group.beyondapp.dto.programhistory.response.DailyHistoryResponse
import com.group.beyondapp.dto.programhistory.response.FourWeeksHistoryResponse
import com.querydsl.core.types.ConstantImpl
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class FourWeeksHistoryQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {


    fun getAllCareTotalAverageRate(userId: Int, createdDate: LocalDate, week: Int): FourWeeksHistoryResponse? {
        return queryFactory
            .select(
                Projections.constructor(
                    FourWeeksHistoryResponse::class.java,
                    Expressions.constant(createdDate),
                    Expressions.constant(createdDate.plusDays(27)),
                    Expressions.asNumber(Expressions.numberTemplate(Double::class.java, "ROUND(AVG(({0}.workOutCount + {0}.meditationCount) / 7.0) * 100)", programHistory)).intValue(),
                    Expressions.asNumber(Expressions.numberTemplate(Double::class.java, "ROUND(AVG({0}.workOutCount / 6.0) * 100)", programHistory)).intValue(),
                    Expressions.asNumber(Expressions.numberTemplate(Double::class.java, "ROUND(AVG({0}.meditationCount / 1.0) * 100)", programHistory)).intValue()
                )
            )
            .from(programHistory)
            .where(
                programHistory.user.id.eq(userId.toLong()),
                programHistory.date.between(createdDate, createdDate.plusDays(27))
            )
            .fetchOne()
    }


}