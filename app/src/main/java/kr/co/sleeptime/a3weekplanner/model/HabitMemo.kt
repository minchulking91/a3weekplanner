package kr.co.sleeptime.a3weekplanner.model

import org.threeten.bp.LocalDate

data class HabitMemo(
        val uuid: String,
        val memo: String,
        val date: LocalDate
)