package com.group.beyondapp.domain.user.app

import com.group.beyondapp.domain.user.User
import javax.persistence.*

@Entity
class AppUser(

    @Enumerated(EnumType.STRING)
    val os: OSType,

    @Enumerated(EnumType.STRING)
    val mode: ModeType,

    val currentVersion: String,

    val currentHash: String,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    val user: User,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
}