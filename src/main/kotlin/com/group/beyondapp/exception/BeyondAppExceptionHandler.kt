package com.group.beyondapp.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BeyondAppExceptionHandler {
    @ExceptionHandler(BaseException::class)
    fun handle(ex: BaseException): ResponseEntity<ErrorResponse?>? {
        return ResponseEntity<ErrorResponse?>(ErrorResponse.of(ex), ex.httpStatus)
    }
}