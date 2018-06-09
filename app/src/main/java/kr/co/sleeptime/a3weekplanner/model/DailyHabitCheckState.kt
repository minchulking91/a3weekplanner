package kr.co.sleeptime.a3weekplanner.model

import kr.co.sleeptime.a3weekplanner.model.value.HabitEventType

data class DailyHabitCheckState(
        val habit: Habit,
        val isChecked: Boolean,
        val memo: String? = null
) {
    companion object {
        fun getCheckedEvent(habit: Habit, habitEvent: HabitEvent?): DailyHabitCheckState {
            return DailyHabitCheckState(habit = habit, isChecked = habitEvent?.eventType == HabitEventType.CHECKED, memo = habitEvent?.memo)
        }
    }
}