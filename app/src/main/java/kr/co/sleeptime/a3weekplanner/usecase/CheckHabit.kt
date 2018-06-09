package kr.co.sleeptime.a3weekplanner.usecase

import io.reactivex.Completable
import io.reactivex.Single
import kr.co.sleeptime.a3weekplanner.model.DailyHabitCheckState
import kr.co.sleeptime.a3weekplanner.repository.HabitEventRepository

/**
 * create habit event (with memo)
 * insert habit event to database
 *
 */
class CheckHabit(private val habitEventRepository: HabitEventRepository) : UseCase<CheckHabit.CheckHabitParams, Completable> {

    override fun execute(params: CheckHabitParams): Completable {
        val habitCheckState = params.habitCheckState
        return Single
                .create<DailyHabitCheckState> {
                    if (habitCheckState.isChecked) {
                        it.onError(IllegalStateException("habit:${habitCheckState.habit.uuid} already checked!"))
                    } else {
                        it.onSuccess(habitCheckState)
                    }
                }
                .map {
                    habitEventRepository.insertCheckHabitEvent(it.habit, params.memo)
                }
                .toCompletable()
    }

    data class CheckHabitParams(
            val habitCheckState: DailyHabitCheckState,
            val memo: String? = null
    )
}