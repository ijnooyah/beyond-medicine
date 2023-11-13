package com.group.beyondapp.service.app

import com.group.beyondapp.dto.app.request.AppRequest
import com.group.beyondapp.dto.app.response.AppResponse
import com.group.beyondapp.repository.app.AppHashInfoQuerydslRepository
import com.group.beyondapp.repository.app.AppVersionInfoQuerydslRepository
import com.group.beyondapp.util.compareVersions
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AppService(
    private val appVersionInfoQuerydslRepository: AppVersionInfoQuerydslRepository,
    private val appHashInfoQuerydslRepository: AppHashInfoQuerydslRepository
) {
    


    @Transactional(readOnly = true)
    fun validateAndUpdateAppIntegrity(request: AppRequest): AppResponse {
        // hash 비교
        val validHash = appHashInfoQuerydslRepository.getHashByOSandMode(request.os, request.mode)
        if (request.hash != validHash) {
            return AppResponse("유효하지 않은 해시", "앱을 실행 할 수 없음")
        }

        val appVersionInfo = appVersionInfoQuerydslRepository.getAppVersionInfo()
        // user의 버전과 최소버전 비교
        if (compareVersions(request.version, appVersionInfo!!.minVersion) < 0) {
            return AppResponse("업데이트 강제", "현재 버전이 실행 가능한 최소버전 보다 낮음")
        }
        // user의 버전과 가장 최신버전 비교
        if (compareVersions(request.version, appVersionInfo!!.latestVersion) < 0) {
            return AppResponse("업데이트 필요", "현재 버전이 가장 최신버전보다 낮음")
        }
        return AppResponse(null, null)
    }

}