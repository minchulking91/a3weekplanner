package kr.co.sleeptime.a3weekplanner.model

import kr.co.sleeptime.a3weekplanner.model.value.HabitEventType
import org.threeten.bp.LocalDateTime
import java.util.*

data class HabitEvent(
        val uuid: String,
        val habitUUID: String,
        val eventAt: LocalDateTime,
        val eventType: HabitEventType,
        val memo: String? = null
) {
    companion object {
        fun getCreateEvent(todo: Habit): HabitEvent {
            return HabitEvent(
                    uuid = UUID.randomUUID().toString(),
                    habitUUID = todo.uuid,
                    eventType = HabitEventType.CREATED,
                    eventAt = LocalDateTime.now()
            )
        }

        fun getCheckEvent(todo: Habit, memo: String?): HabitEvent {
            return HabitEvent(
                    uuid = UUID.randomUUID().toString(),
                    habitUUID = todo.uuid,
                    eventType = HabitEventType.CHECKED,
                    eventAt = LocalDateTime.now(),
                    memo = memo
            )
        }

        fun getCancelEvent(todo: Habit): HabitEvent {
            return HabitEvent(
                    uuid = UUID.randomUUID().toString(),
                    habitUUID = todo.uuid,
                    eventType = HabitEventType.CANCELED,
                    eventAt = LocalDateTime.now()
            )
        }
    }
}