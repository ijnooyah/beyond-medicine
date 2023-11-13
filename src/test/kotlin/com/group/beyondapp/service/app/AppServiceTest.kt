package com.group.beyondapp.service.app

import com.group.beyondapp.domain.user.app.AppHashInfo
import com.group.beyondapp.domain.user.app.AppHashInfoRepository
import com.group.beyondapp.domain.user.app.AppVersionInfo
import com.group.beyondapp.domain.user.app.AppVersionInfoRepository
import com.group.beyondapp.dto.app.request.AppRequest
import com.group.beyondapp.util.generateHash
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AppServiceTest@Autowired constructor(
    private val appVersionInfoRepository: AppVersionInfoRepository,
    private val appHashInfoRepository: AppHashInfoRepository,
    private val appService: AppService,
) {
    @AfterEach
    fun clean() {
        appVersionInfoRepository.deleteAll()
        appHashInfoRepository.deleteAll()
    }

    @Test
    @DisplayName("앱 무결성 검증 및 버전확인을 한다.")
    fun validateAndUpdateAppIntegrity() {
        // given
        val minVersion = "2.0.1"
        val latestVersion = "2.2.1"
        appVersionInfoRepository.save(AppVersionInfo(minVersion, latestVersion))
        appHashInfoRepository.saveAll(listOf(
            AppHashInfo("ios", "release", generateHash("ios", "release")),
            AppHashInfo("ios", "debug", generateHash("ios", "debug")),
            AppHashInfo("android", "release", generateHash("android", "release")),
            AppHashInfo("android", "debug", generateHash("android", "debug")),
        ))

        //when
        val version = "2.0.1"
        val os = "Android"
        val mode = "release"
        val hash = generateHash(os, mode)
        val request = AppRequest(version, os, mode, hash)
        val appResponse = appService.validateAndUpdateAppIntegrity(request)
        println("appResponse: $appResponse")

        // then



    }
}