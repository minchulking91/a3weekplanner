package kr.co.sleeptime.a3weekplanner.usecase

import io.reactivex.Single
import kr.co.sleeptime.a3weekplanner.model.Habit
import kr.co.sleeptime.a3weekplanner.repository.HabitRepository
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

/**
 * 습관 만들기
 */
class CreateHabit(private val habitRepository: HabitRepository) : UseCase<CreateHabit.CreateHabitParams, Single<Habit>> {
    override fun execute(params: CreateHabitParams): Single<Habit> {
        return habitRepository.createHabit(
                title = params.title,
                startDate = params.startDate,
                alarmTime = params.alarmTime,
                description = params.description
        )
    }

    data class CreateHabitParams(
            val title: String,
            val startDate: LocalDate,
            val alarmTime: LocalTime?,
            val description: String?
    )
}