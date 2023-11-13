package com.group.beyondapp.dto.app.request

data class AppRequest(
  val version: String,
  val os: String,
  val mode: String,
  val hash: String,
)