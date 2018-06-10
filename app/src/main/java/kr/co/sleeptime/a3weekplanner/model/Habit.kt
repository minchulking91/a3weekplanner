package kr.co.sleeptime.a3weekplanner.model

import kr.co.sleeptime.a3weekplanner.FINISH_AFTER_DAYS
import kr.co.sleeptime.a3weekplanner.model.value.HabitState
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import java.util.*


data class Habit(
        val uuid: String,
        val startDate: LocalDate,
        val endDate: LocalDate,
        val title: String,
        val alarmTime: LocalTime? = null,
        val description: String? = null,
        val createdAt: LocalDateTime,
        val canceledAt: LocalDateTime? = null,
        val isWeekdaysOnly: Boolean,
        val checkCount: Int = 0
) {
    companion object {
        fun createHabit(title: String, startDate: LocalDate, alarmTime: LocalTime?, description: String?, isWeekdaysOnly: Boolean): Habit {
            return Habit(uuid = UUID.randomUUID().toString(),
                    title = title,
                    startDate = startDate,
                    endDate = startDate.plusDays(FINISH_AFTER_DAYS),
                    alarmTime = alarmTime,
                    description = description,
                    createdAt = LocalDateTime.now(),
                    isWeekdaysOnly = isWeekdaysOnly)
        }
    }

    val isCanceled: Boolean get() = canceledAt != null
    val state: HabitState
        get() {
            val today = LocalDate.now()
            when {
                isCanceled -> {
                    return HabitState.CANCELED
                }
                startDate.isAfter(today) -> {
                    return HabitState.SCHEDULED
                }
                checkIsIn(today) -> {
                    return HabitState.PROGRESS
                }
            }
            return HabitState.COMPLETE
        }

    fun checkIsIn(queryDate: LocalDate): Boolean {
        return !startDate.isAfter(queryDate) && !endDate.isBefore(queryDate)
    }
}
