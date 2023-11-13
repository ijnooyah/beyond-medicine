package com.group.beyondapp.exception

import org.springframework.http.HttpStatus

enum class EnumErrorCode(status: HttpStatus, message: String) {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INVALID_HASH(HttpStatus.BAD_REQUEST, "유효하지 않은 해시입니다."),
    NEED_UPDATE(HttpStatus.BAD_REQUEST, "현재 버전이 실행가능한 최소버전보다 낮습니다."),

    OK(HttpStatus.OK, "요청 성공")
    ;

    public val status: HttpStatus = status
    public val message: String = message
}