package com.group.beyondapp.exception

import org.springframework.http.HttpStatus

enum class EnumErrorCode(result: Int, message: String) {
    NOT_FOUND_USER(100001, "해당하는 사용자가 없습니다."),
    INVALID_HASH(200001, "유효하지 않은 해시입니다."),
    NEED_UPDATE(200002, "현재 버전이 실행가능한 최소버전보다 낮습니다."),
    INVALID_WEEK(300001, "기한이 지나지 않아 해당 데이터를 조회할 수 없습니다."),

    SUCCESS(1, "요청 성공")
    ;

    val result: Int = result
    val message: String = message
}