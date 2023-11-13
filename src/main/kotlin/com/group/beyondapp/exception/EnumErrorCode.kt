package com.group.beyondapp.exception

import org.springframework.http.HttpStatus

enum class EnumErrorCode(status: HttpStatus, message: String) {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

    OK(HttpStatus.OK, "요청 성공")
    ;

    public val status: HttpStatus = status
    public val message: String = message
}