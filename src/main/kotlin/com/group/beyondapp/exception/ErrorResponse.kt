package com.group.beyondapp.exception

import org.springframework.http.HttpStatus

class ErrorResponse(
    val result: HttpStatus,
    val message: String
) {

}