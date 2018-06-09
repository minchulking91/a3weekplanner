package kr.co.sleeptime.a3weekplanner.app

import android.app.Application
import androidx.room.Room
import kr.co.sleeptime.a3weekplanner.database.PlannerDatabase
import kr.co.sleeptime.a3weekplanner.repository.HabitEventRepository
import kr.co.sleeptime.a3weekplanner.repository.HabitRepository
import kr.co.sleeptime.a3weekplanner.usecase.*

class AppProvider(private val app: Application) {

    private val database: PlannerDatabase by lazy { Room.databaseBuilder(app, PlannerDatabase::class.java, "planner").build() }
    private val habitRepository: HabitRepository by lazy { HabitRepository(database) }
    private val habitEventRepository: HabitEventRepository by lazy { HabitEventRepository(database) }

    /*
    UseCase
     */
    fun getCreateHabit(): CreateHabit {
        return CreateHabit(habitRepository)
    }

    fun getCheckHabit(): CheckHabit {
        return CheckHabit(habitEventRepository)
    }

    fun getCheckListByDate(): TodayCheckList {
        return TodayCheckList(habitRepository, habitEventRepository)
    }

    fun getHistory(): LoadHistory {
        return LoadHistory()
    }

    fun getHistoryByDate(): HistoryByDate {
        return HistoryByDate()
    }
}