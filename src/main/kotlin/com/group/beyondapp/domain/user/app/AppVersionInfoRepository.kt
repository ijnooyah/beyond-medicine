package com.group.beyondapp.domain.user.app

import org.springframework.data.jpa.repository.JpaRepository

interface AppVersionInfoRepository : JpaRepository<AppVersionInfo, Long> {

}