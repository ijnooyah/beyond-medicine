package com.group.beyondapp.exception

import org.springframework.http.HttpStatus

abstract class ClientException : BaseException {
    constructor(enumErrorCode: EnumErrorCode) : super(enumErrorCode)
    constructor(result: Int, message: String) : super(result, message)

    abstract override val httpStatus: HttpStatus

    class BadRequest(enumErrorCode: EnumErrorCode) : ClientException(enumErrorCode) {
        override val httpStatus: HttpStatus
            get() = HttpStatus.BAD_REQUEST
    }

    class Unauthorized(enumErrorCode: EnumErrorCode) : ClientException(enumErrorCode) {
        override val httpStatus: HttpStatus
            get() = HttpStatus.UNAUTHORIZED
    }

    class Forbidden(enumErrorCode: EnumErrorCode) : ClientException(enumErrorCode) {
        override val httpStatus: HttpStatus
            get() = HttpStatus.FORBIDDEN
    }

    class NotFound(enumErrorCode: EnumErrorCode) : ClientException(enumErrorCode) {
        override val httpStatus: HttpStatus
            get() = HttpStatus.NOT_FOUND
    }

    class Conflict(enumErrorCode: EnumErrorCode) : ClientException(enumErrorCode) {
        override val httpStatus: HttpStatus
            get() = HttpStatus.CONFLICT
    }
}