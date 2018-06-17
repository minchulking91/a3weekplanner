package kr.co.sleeptime.a3weekplanner.model

import kr.co.sleeptime.a3weekplanner.model.value.HabitEventType
import org.threeten.bp.LocalDate

/**
 * @param eventType [HabitEventType.NEED_CHECK] or [HabitEventType.CHECKED] or [HabitEventType.FAIL]
 */
data class TodayHabit(
        val habit: Habit,
        val eventType: HabitEventType,
        val memo: String? = null,
        val endDate: LocalDate
) {
    val progress: Float
        get() {
            val totalCount = if (habit.isWeekdaysOnly) 15 else 21
            return habit.checkCount.toFloat() / totalCount.toFloat()
        }
}