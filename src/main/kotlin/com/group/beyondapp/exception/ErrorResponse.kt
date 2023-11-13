package com.group.beyondapp.exception

class ErrorResponse(
    val result: Int,
    val message: String
) {

    companion object {
        fun of(ex: BaseException): ErrorResponse {
            return ErrorResponse(ex.result, ex.message)
        }
    }
}