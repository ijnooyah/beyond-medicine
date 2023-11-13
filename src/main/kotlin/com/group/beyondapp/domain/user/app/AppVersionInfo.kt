package com.group.beyondapp.domain.user.app

import javax.persistence.*

@Entity
class AppVersionInfo(

    val minVersion: String,

    val latestVersion: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
}