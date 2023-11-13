package com.group.beyondapp.repository.app

import com.group.beyondapp.domain.user.app.AppVersionInfo
import com.group.beyondapp.domain.user.app.QAppVersionInfo.appVersionInfo
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class AppVersionInfoQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {


    fun getAppVersionInfo(): AppVersionInfo? {
        return queryFactory.select(appVersionInfo)
            .from(appVersionInfo)
            .limit(1)
            .fetchOne()
    }
}