package com.group.beyondapp.exception

import org.springframework.http.HttpStatus

abstract class ServerException(enumErrorCode: EnumErrorCode) : BaseException(enumErrorCode)

class InternalServerError(enumErrorCode: EnumErrorCode) : ServerException(enumErrorCode) {
    override val httpStatus: HttpStatus
        get() = HttpStatus.INTERNAL_SERVER_ERROR
}