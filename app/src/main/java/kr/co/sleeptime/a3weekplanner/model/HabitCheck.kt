package kr.co.sleeptime.a3weekplanner.model

import org.threeten.bp.LocalDateTime

data class HabitCheck(
        val uuid: String,
        val checkedAt: LocalDateTime
)