package kr.co.sleeptime.a3weekplanner.model

import kr.co.sleeptime.a3weekplanner.model.value.HabitEventType

/**
 * @param eventType [HabitEventType.NEED_CHECK] or [HabitEventType.CHECKED] or [HabitEventType.FAIL]
 */
data class TodayHabit(
        val habit: Habit,
        val memo: HabitMemo? = null,
        val habitCheck: HabitCheck? = null
) {
}