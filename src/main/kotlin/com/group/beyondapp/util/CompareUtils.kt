package com.group.beyondapp.util

fun compareVersions(currentVersion: String, targetVersion: String): Int {
    val currentParts = currentVersion.split(".").map { it.toInt() }
    val targetParts = targetVersion.split(".").map { it.toInt() }

    for (i in 0 until minOf(currentParts.size, targetParts.size)) {
        if (currentParts[i] != targetParts[i]) {
            return currentParts[i].compareTo(targetParts[i])
        }
    }

    return currentParts.size.compareTo(targetParts.size)
}