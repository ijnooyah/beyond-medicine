package com.group.beyondapp.exception

class BaseException(enumErrorCode: EnumErrorCode): RuntimeException() {
    val enumErrorCode: EnumErrorCode = enumErrorCode
}