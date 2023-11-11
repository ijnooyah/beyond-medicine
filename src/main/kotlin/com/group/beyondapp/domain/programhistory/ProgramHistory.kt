package com.group.beyondapp.domain.programhistory

import com.group.beyondapp.domain.BaseTimeEntity
import com.group.beyondapp.domain.user.User
import java.time.LocalDate
import javax.persistence.*


@Entity
class ProgramHistory(

    @ManyToOne
    val user: User,

    val date: LocalDate,

    val workOutCount: Int,

    val meditationCount: Int,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    ) : BaseTimeEntity() {


}