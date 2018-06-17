package kr.co.sleeptime.a3weekplanner.repository

import io.reactivex.Flowable
import io.reactivex.Single
import kr.co.sleeptime.a3weekplanner.database.dao.HabitDAO
import kr.co.sleeptime.a3weekplanner.database.mapper.HabitMapper
import kr.co.sleeptime.a3weekplanner.model.Habit
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime


class HabitRepository(private val habitDao: HabitDAO) {

    fun createHabit(title: String, startDate: LocalDate, alarmTime: LocalTime?, description: String?, weekdaysOnly: Boolean): Single<Habit> {
        return Single.create<Habit> {
            try {
                val newHabit = Habit.createHabit(title, startDate, alarmTime, description, weekdaysOnly)
                habitDao.insertHabit(newHabit.let { HabitMapper.toEntity(it) })
                it.onSuccess(newHabit)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    fun loadByDate(queryDate: LocalDate): Single<List<Habit>> {
        return habitDao.loadHabitsByDate(queryDate).map { HabitMapper.toModel(it) }
    }

    fun cancelHabit(habit: Habit, canceledAt: LocalDateTime): Single<Habit> {
        val copy = habit.copy(canceledAt = canceledAt)
        return updateHabit(copy)
    }

    fun updateAlarm(habit: Habit, alarmTime: LocalTime?): Single<Habit> {
        val copy = habit.copy(alarmTime = alarmTime)
        return updateHabit(copy)
    }

    fun updateHabit(newHabit: Habit): Single<Habit> {
        return Single.create<Habit> {
            try {
                habitDao.updateHabit(newHabit.let { HabitMapper.toEntity(it) })
                it.onSuccess(newHabit)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }
}