package com.group.beyondapp.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDate = LocalDate.now()

    @LastModifiedDate
    @Column(nullable = false)
    var modifiedAt: LocalDate = LocalDate.now()

    fun updateCreatedAt(createdAt: LocalDate) {
        this.createdAt = createdAt
    }

}