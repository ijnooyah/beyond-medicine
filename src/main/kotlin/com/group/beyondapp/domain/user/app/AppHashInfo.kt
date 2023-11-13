package com.group.beyondapp.domain.user.app

import javax.persistence.*

@Entity
class AppHashInfo(
    val os: String,

    val mode: String,

    val hash: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {

}