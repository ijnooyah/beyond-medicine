package com.group.beyondapp.controller.app

import com.group.beyondapp.dto.app.request.AppRequest
import com.group.beyondapp.dto.app.response.AppResponse
import com.group.beyondapp.dto.programhistory.request.ProgramHistoryRequest
import com.group.beyondapp.dto.programhistory.response.ProgramHistoryResponse
import com.group.beyondapp.service.app.AppService
import com.group.beyondapp.service.programhistory.ProgramHistoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AppController(
    private val appService: AppService,
) {

    @PostMapping("/api/validate")
    fun validateAndUpdateAppIntegrity(@RequestBody request: AppRequest): AppResponse {
        return appService.validateAndUpdateAppIntegrity(request)
    }
}