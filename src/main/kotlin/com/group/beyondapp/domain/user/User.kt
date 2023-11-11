package com.group.beyondapp.domain.user

import com.group.beyondapp.domain.BaseTimeEntity
import com.group.beyondapp.domain.programhistory.ProgramHistory
import javax.persistence.*

@Entity
class User(

    var name: String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val userProgramHistories: MutableList<ProgramHistory> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) : BaseTimeEntity() {

}