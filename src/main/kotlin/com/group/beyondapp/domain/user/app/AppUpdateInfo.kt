package com.group.beyondapp.domain.user.app

import com.group.beyondapp.domain.user.User
import javax.persistence.*

@Entity
class AppUpdateInfo(

    val minVersion: String,

    val latestVersion: String,

    @Column(columnDefinition = "JSON")
    val latestHashes: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
}