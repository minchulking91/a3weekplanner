package kr.co.sleeptime.a3weekplanner.repository

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kr.co.sleeptime.a3weekplanner.database.PlannerDatabase
import kr.co.sleeptime.a3weekplanner.database.mapper.HabitEventMapper
import kr.co.sleeptime.a3weekplanner.database.mapper.HabitMapper
import kr.co.sleeptime.a3weekplanner.model.Habit
import kr.co.sleeptime.a3weekplanner.model.HabitEvent
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime


class HabitRepository(private val database: PlannerDatabase) {

    /**
     * 진행사항 불러오기
     * @param date 조회할 날짜
     *
     */
    fun getHabitsByDate(date: LocalDate = LocalDate.now()): Single<List<Habit>> {
        return Single
                .create<List<Habit>> {
                    try {
                        it.onSuccess(database.habitDAO().loadHabitsByDate(date.toString()).map { HabitMapper.toModel(it) })
                    } catch (e: Exception) {
                        it.onError(e)
                    }
                }
                .subscribeOn(Schedulers.io())
    }

    fun getHabitByUUID(uuid: String): Single<Habit> {
        return Single
                .create<Habit> {
                    try {
                        it.onSuccess(HabitMapper.toModel(database.habitDAO().loadHabit(uuid)))
                    } catch (e: Exception) {
                        it.onError(e)
                    }
                }
                .subscribeOn(Schedulers.io())
    }

    /**
     * 습관 만들기
     * @param title 습관 제목
     * @param startDate 시작 날짜 (default = today)
     * @param alarmTime 알림 시간
     * @param description 세부사항
     * @return 만들어진 습관
     */
    fun createHabit(title: String, startDate: LocalDate, alarmTime: LocalTime? = null, description: String? = null): Single<Habit> {
        return Single.create<Habit> { entity ->
            try {
                val todo = Habit.createHabit(title, startDate, alarmTime, description)
                val todoEvent = HabitEvent.getCreateEvent(todo)
                database.habitDAO().insertHabit(HabitMapper.toEntity(todo))
                database.habitEventDAO().insertHabitEvent(HabitEventMapper.toEntity(todoEvent))
                entity.onSuccess(todo)
            } catch (e: Exception) {
                entity.onError(e)
            }
        }
    }

    fun deleteHabit(todo: Habit): Completable {
        return Completable.fromAction {
            val todoEntity = HabitMapper.toEntity(todo)
            database.habitDAO().deleteHabit(todoEntity)
        }
    }

    fun cancelHabit(todo: Habit): Single<Habit> {
        return Single.fromCallable {
            val newHabit = todo.copy(isCanceled = true)
            database.habitDAO().updateHabit(HabitMapper.toEntity(newHabit))
            database.habitEventDAO().insertHabitEvent(HabitEventMapper.toEntity(HabitEvent.getCancelEvent(newHabit)))
            newHabit
        }

    }

}