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
) {

    companion object {
        fun fromHabitEvent(habit: Habit, habitEvent: HabitEvent): HabitHistory {
            val eventAt = habitEvent.eventAt
            return HabitHistory(habit = habit, historyDate = eventAt.toLocalDate(), historyTime = eventAt.toLocalTime(), memo = habitEvent.memo, eventType = habitEvent.eventType)
        }

        fun failHistory(habit: Habit, date: LocalDate): HabitHistory {
            return HabitHistory(habit = habit, historyDate = date, historyTime = null, memo = null, eventType = HabitEventType.FAIL)
        }

        fun needCheckHistory(habit: Habit, date:LocalDate):HabitHistory{
            return HabitHistory(habit = habit, historyDate = date, historyTime = null, memo = null, eventType = HabitEventType.NEED_CHECK)
        }
    }
}