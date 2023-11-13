package com.group.beyondapp.service.app

import com.group.beyondapp.domain.user.app.AppHashInfo
import com.group.beyondapp.domain.user.app.AppHashInfoRepository
import com.group.beyondapp.domain.user.app.AppVersionInfo
import com.group.beyondapp.domain.user.app.AppVersionInfoRepository
import com.group.beyondapp.dto.app.request.AppRequest
import com.group.beyondapp.exception.BaseException
import com.group.beyondapp.util.generateHash
import org.assertj.core.api.AssertionsForInterfaceTypes
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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
    @DisplayName("유효하지 않은 해시라면 에러를 던진다")
    fun validateHashTest() {
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

        //when & then
        val version = "2.0.1"
        val os = "adfad"
        val mode = "release"
        val hash = generateHash(os, mode)
        val request = AppRequest(version, os, mode, hash)
        val message = assertThrows<BaseException> {
            appService.validateAndUpdateAppIntegrity(request)
        }.message
        AssertionsForInterfaceTypes.assertThat(message).isEqualTo("유효하지 않은 해시입니다.")

    }

    @Test
    @DisplayName("최소 버전보다 낮으면 에러를 던진다")
    fun validateMinVersionTest() {
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

        //when & then
        val version = "2.0.0"
        val os = "Android"
        val mode = "release"
        val hash = generateHash(os, mode)
        val request = AppRequest(version, os, mode, hash)
        val message = assertThrows<BaseException> {
            appService.validateAndUpdateAppIntegrity(request)
        }.message
        AssertionsForInterfaceTypes.assertThat(message).isEqualTo("현재 버전이 실행가능한 최소버전보다 낮습니다.")
    }

    @Test
    @DisplayName("최신 버전보다 낮으면 업데이트 권유를 한다")
    fun validateLatestVersionTest() {
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
        val result = appService.validateAndUpdateAppIntegrity(request)

        //then
        assertThat(result.title).isEqualTo("업데이트 필요")
        assertThat(result.content).isEqualTo("현재 버전이 가장 최신버전보다 낮음")
    }

    @Test
    @DisplayName("현재 버전이 최신버전 이상일때")
    fun validateVersionTest() {
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
        val version = "2.2.1"
        val os = "Android"
        val mode = "release"
        val hash = generateHash(os, mode)
        val request = AppRequest(version, os, mode, hash)
        val result = appService.validateAndUpdateAppIntegrity(request)

        //then
        assertThat(result.title).isNull()
        assertThat(result.content).isNull()
    }
}