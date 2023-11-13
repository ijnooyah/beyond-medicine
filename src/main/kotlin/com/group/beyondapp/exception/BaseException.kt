package com.group.beyondapp.exception

import org.springframework.http.HttpStatus

abstract class BaseException : RuntimeException {
    var result: Int
    override var message: String

    constructor(enumErrorCode: EnumErrorCode) {
        result = enumErrorCode.result
        message = enumErrorCode.message
    }

    constructor(enumErrorCode: EnumErrorCode, ex: Throwable?) {
        result = enumErrorCode.result
        message = enumErrorCode.message
        initCause(ex)
    }

    constructor(result: Int, message: String) {
        this.result = result
        this.message = message
    }

    abstract val httpStatus: HttpStatus?
}