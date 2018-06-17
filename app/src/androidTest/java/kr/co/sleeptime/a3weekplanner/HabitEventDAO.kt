package kr.co.sleeptime.a3weekplanner

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import kr.co.sleeptime.a3weekplanner.database.PlannerDatabase
import kr.co.sleeptime.a3weekplanner.database.dao.HabitDAO
import org.junit.After
import org.junit.Before
import org.junit.Rule

class HabitEventDAO{

    lateinit var habitDAO: HabitDAO
    lateinit var database: PlannerDatabase

    val instantTaskExecutorRule = InstantTaskExecutorRule()
        @Rule get

    @Before
    fun beforeTest() {
        val appContext = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(appContext, PlannerDatabase::class.java).allowMainThreadQueries().build()
        habitDAO = database.habitDAO()
    }

    @After
    fun afterTest() {
        database.close()
    }

}