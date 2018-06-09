package kr.co.sleeptime.a3weekplanner.model

import kr.co.sleeptime.a3weekplanner.FINISH_AFTER_DAYS
import kr.co.sleeptime.a3weekplanner.model.value.HabitState
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import java.util.*


data class Habit(
        val uuid: String,
        val startDate: LocalDate,
        val title: String,
        val alarmTime: LocalTime? = null,
        val description: String? = null,
        val createdAt: Instant,
        val isCanceled: Boolean
) {
    companion object {
        fun createHabit(title: String, startDate: LocalDate, alarmDateTime: LocalTime?, description: String?): Habit {
            return Habit(uuid = UUID.randomUUID().toString(),
                    title = title,
                    startDate = startDate,
                    alarmTime = alarmDateTime,
                    description = description,
                    createdAt = Instant.now(),
                    isCanceled = false)
        }
    }

    val state: HabitState
        get() {
            if (isCanceled) {
                return HabitState.CANCELED
            }
            val today = LocalDate.now()
            if (startDate.isAfter(today)) {
                return HabitState.SCHEDULED
            }
            if (!startDate.isAfter(today) && !startDate.plusDays(FINISH_AFTER_DAYS).isBefore(today)) {
                return HabitState.PROGRESS
            }
            return HabitState.COMPLETE
        }
}
