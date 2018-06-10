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

}