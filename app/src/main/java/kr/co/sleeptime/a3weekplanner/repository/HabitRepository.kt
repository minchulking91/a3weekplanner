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


}