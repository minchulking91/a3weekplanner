package kr.co.sleeptime.a3weekplanner.app

import android.app.Application
import androidx.room.Room
import kr.co.sleeptime.a3weekplanner.database.PlannerDatabase
import kr.co.sleeptime.a3weekplanner.repository.HabitEventRepository
import kr.co.sleeptime.a3weekplanner.repository.HabitRepository

class AppProvider(private val app: Application) {

    private val database: PlannerDatabase by lazy { Room.databaseBuilder(app, PlannerDatabase::class.java, "planner").build() }
    private val habitRepository: HabitRepository by lazy { HabitRepository(database.habitDAO()) }
    private val habitEventRepository: HabitEventRepository by lazy { HabitEventRepository(database.habitEventDAO()) }

}