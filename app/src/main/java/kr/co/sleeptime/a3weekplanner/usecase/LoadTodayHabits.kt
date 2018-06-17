package kr.co.sleeptime.a3weekplanner.usecase

import io.reactivex.Single
import kr.co.sleeptime.a3weekplanner.model.TodayHabit
import kr.co.sleeptime.a3weekplanner.repository.HabitEventRepository
import kr.co.sleeptime.a3weekplanner.repository.HabitRepository
import org.threeten.bp.LocalDate

class LoadTodayHabits(private val habitRepository: HabitRepository, private val habitEventRepository: HabitEventRepository) : UseCase<LoadTodayHabits.LoadTodayHabitsParams, Single<List<TodayHabit>>> {
    override fun execute(params: LoadTodayHabitsParams): Single<List<TodayHabit>> {
        val queryDate = params.queryDate
        return habitRepository.loadByDate(queryDate)
                .flatMap { habits ->
                    habitEventRepository.habitChecksByDate(queryDate)
                            .flatMap { habitChecks ->
                                habitEventRepository.getMemosByDate(queryDate).map { habitChecks to it }
                            }
                            .map {
                                habits to it
                            }
                }
                .map {
                    val habits = it.first
                    val habitChecks = it.second.first.associateBy { it.uuid }
                    val habitMemos = it.second.second.associateBy { it.uuid }
                    habits.map {
                        TodayHabit(it, habitMemos[it.uuid], habitChecks[it.uuid])
                    }
                }
    }

    data class LoadTodayHabitsParams(
            val queryDate: LocalDate
    )
}