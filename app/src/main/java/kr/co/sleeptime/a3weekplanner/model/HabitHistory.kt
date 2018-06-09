package kr.co.sleeptime.a3weekplanner.model

import kr.co.sleeptime.a3weekplanner.model.value.HabitEventType
import org.threeten.bp.LocalDate

data class HabitHistory(
        val habitUUID: String,
        private val habitEvent: HabitEvent?,
        val historyDate: LocalDate
) {

    val state: HabitEventType
        get() {
            return habitEvent?.eventType ?: HabitEventType.NEED_CHECK
        }

    companion object {
        fun fromHabitEvent(habitEvent: HabitEvent): HabitHistory {
//            return HabitHistory(habitEvent = habitEvent)
            TODO()
        }
    }
}