package com.group.beyondapp.controller.programhistory

import com.group.beyondapp.dto.programhistory.request.ProgramHistoryRequest
import com.group.beyondapp.dto.programhistory.response.ProgramHistoryResponse
import com.group.beyondapp.service.programhistory.ProgramHistoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProgramHistoryController(
    private val programHistoryService: ProgramHistoryService,
) {

    @GetMapping("/programs")
    fun getCareProgramParticipationRate(@RequestBody request: ProgramHistoryRequest): ProgramHistoryResponse {
        return programHistoryService.getUserProgramHistory(request)
    }
}