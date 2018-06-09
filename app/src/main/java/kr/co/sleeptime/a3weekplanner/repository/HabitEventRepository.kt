package kr.co.sleeptime.a3weekplanner.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import kr.co.sleeptime.a3weekplanner.database.PlannerDatabase
import kr.co.sleeptime.a3weekplanner.database.mapper.HabitEventMapper
import kr.co.sleeptime.a3weekplanner.model.Habit
import kr.co.sleeptime.a3weekplanner.model.HabitEvent
import kr.co.sleeptime.a3weekplanner.model.value.HabitEventType
import org.threeten.bp.LocalDate

class HabitEventRepository(private val database: PlannerDatabase) {
    fun insertCheckHabitEvent(habit: Habit, memo:String? = null): Completable {
        return Single.create<Habit> {
            it.onSuccess(habit)
        }.flatMapCompletable {
            Completable.create {
                try {
                    val checkEvent = HabitEvent.getCheckEvent(habit, memo)
                    database.habitEventDAO().insertHabitEvent(HabitEventMapper.toEntity(checkEvent))
                    it.onComplete()
                } catch (e: Exception) {
                    it.onError(e)
                }
            }
        }
    }

    fun getHabitEvents(todoId: String): Flowable<HabitEvent> {
        return database.habitEventDAO().loadHabitEvent(todoId).map { HabitEventMapper.toModel(it) }
    }

    fun getHabitEventsByDate(queryDate: LocalDate): Single<List<HabitEvent>> {
        return Single.create {
            try {
                it.onSuccess(database.habitEventDAO().loadHabitEventsByDate(queryDate.toString()).map { HabitEventMapper.toModel(it) })
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    fun getHabitEvents(queryDate: LocalDate, habitEventType: HabitEventType): Single<List<HabitEvent>> {
        return Single.create {
            it.onSuccess(database.habitEventDAO().loadHabitEvents(queryDate.toString(), habitEventType.toString()).map { HabitEventMapper.toModel(it) })
        }
    }
}