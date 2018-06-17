package kr.co.sleeptime.a3weekplanner.repository

import io.reactivex.Single
import kr.co.sleeptime.a3weekplanner.database.dao.HabitEventDAO
import kr.co.sleeptime.a3weekplanner.database.mapper.HabitEventMapper
import kr.co.sleeptime.a3weekplanner.model.Habit
import kr.co.sleeptime.a3weekplanner.model.HabitCheck
import kr.co.sleeptime.a3weekplanner.model.HabitMemo
import kr.co.sleeptime.a3weekplanner.model.value.HabitState
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

class HabitEventRepository(private val habitEventDAO: HabitEventDAO) {

    fun updateHabitMemo(habit: Habit, habitMemo: String, date: LocalDate): Single<HabitMemo> {
        return Single.create<HabitMemo> {
            try {
                val newMemo = HabitMemo(habit.uuid, habitMemo, date)
                habitEventDAO.insertHabitMemo(newMemo.let { HabitEventMapper.toEntity(newMemo) })
                it.onSuccess(newMemo)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    fun getMemosByHabit(habit: Habit): Single<List<HabitMemo>> {
        return habitEventDAO.loadHabitMemosByUUID(habit.uuid).map { HabitEventMapper.toMemoModelList(it) }
    }

    fun getMemosByDate(queryDate: LocalDate): Single<List<HabitMemo>> {
        return habitEventDAO.loadHabitMemosByDate(queryDate).map { HabitEventMapper.toMemoModelList(it) }
    }

    fun insertHabitCheck(habit: Habit, checkedAt: LocalDateTime, queryAt: LocalDate = LocalDate.now()): Single<HabitCheck> {
        return Single.create {
            try {
                if (habit.getState(queryAt) != HabitState.PROGRESS) {
                    throw IllegalStateException("habit state must be PROGRESS")
                }
                val check = HabitCheck(habit.uuid, checkedAt)
                habitEventDAO.insertHabitCheck(check.let { HabitEventMapper.toEntity(it) })
                it.onSuccess(check)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    fun habitChecksByDate(queryDate: LocalDate): Single<List<HabitCheck>> {
        return habitEventDAO.loadHabitChecksByDaysBetween(queryDate, queryDate.plusDays(1)).map { HabitEventMapper.toCheckModelList(it) }
    }

    fun habitChecksByHabit(habit: Habit): Single<List<HabitCheck>> {
        return habitEventDAO.loadHabitChecksByUUID(habit.uuid).map { HabitEventMapper.toCheckModelList(it) }
    }

}