package com.group.beyondapp.domain.user.app

import org.springframework.data.jpa.repository.JpaRepository

interface AppUserRepository : JpaRepository<AppUser, Long> {

}