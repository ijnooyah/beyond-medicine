package com.group.beyondapp.util

import java.security.MessageDigest
import java.util.*

fun generateHash(os: String, mode: String): String {
    val data = "${os.lowercase()}-${mode.lowercase()}".toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val hashBytes = md.digest(data)

    // 바이트 배열을 16진수 문자열로 변환
    val hashStringBuilder = StringBuilder()
    for (byte in hashBytes) {
        hashStringBuilder.append(String.format("%02x", byte))
    }

    return hashStringBuilder.toString()
}