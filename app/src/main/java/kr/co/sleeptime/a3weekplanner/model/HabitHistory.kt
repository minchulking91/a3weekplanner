package kr.co.sleeptime.a3weekplanner.model

import kr.co.sleeptime.a3weekplanner.model.value.HabitEventType
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

data class HabitHistory(
        val habit: Habit,
        val historyDate: LocalDate,
        val historyTime: LocalTime?,
        val memo: String?,
        val eventType: HabitEventType
)