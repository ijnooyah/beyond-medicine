package com.group.beyondapp.domain.user

import com.group.beyondapp.domain.BaseTimeEntity
import com.group.beyondapp.domain.programhistory.ProgramHistory
import com.group.beyondapp.domain.user.app.AppUser
import javax.persistence.*

@Entity
class User(

    var name: String,

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    val appUser: AppUser? = null,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val userProgramHistories: MutableList<ProgramHistory> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) : BaseTimeEntity() {

}