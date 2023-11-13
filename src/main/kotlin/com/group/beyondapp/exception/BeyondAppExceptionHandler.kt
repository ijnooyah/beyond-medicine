package com.group.beyondapp.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BeyondAppExceptionHandler {
    @ExceptionHandler(BaseException::class)
    protected fun handleBaseException(e: BaseException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(e.enumErrorCode.status)
            .body(ErrorResponse(e.enumErrorCode.status, e.enumErrorCode.message))
    }
}