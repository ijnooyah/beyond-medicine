package com.group.beyondapp.repository.programhistory

import com.group.beyondapp.domain.programhistory.QProgramHistory.programHistory
import com.group.beyondapp.dto.programhistory.response.TodayResponse
import com.querydsl.core.types.ConstantImpl
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class ProgramHistoryQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {

    fun getTodayWorkOutAndMeditation(userId: Int, createdDate: LocalDate): TodayResponse? {
        return queryFactory
            .select(
                Projections.constructor(
                    TodayResponse::class.java,
                    programHistory.date,
                    Expressions.asNumber(Expressions.numberTemplate(Integer::class.java, "ABS(DATEDIFF('DAY', {0}, {1})) + 1", ConstantImpl.create(createdDate), programHistory.date)).intValue(),
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