package com.group.beyondapp.repository.app

import com.group.beyondapp.domain.user.app.QAppHashInfo.appHashInfo
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class AppHashInfoQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {


    fun getHashByOSandMode(os: String, mode: String): String? {
        return queryFactory.select(appHashInfo.hash)
            .from(appHashInfo)
            .where(
                appHashInfo.os.eq(os.lowercase()),
                appHashInfo.mode.eq(mode.lowercase())
            )
            .fetchOne()
    }
}