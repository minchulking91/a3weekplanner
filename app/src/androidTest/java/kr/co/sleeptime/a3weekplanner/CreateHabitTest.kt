package kr.co.sleeptime.a3weekplanner

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import kr.co.sleeptime.a3weekplanner.database.PlannerDatabase
import kr.co.sleeptime.a3weekplanner.repository.HabitRepository
import kr.co.sleeptime.a3weekplanner.usecase.CreateHabit
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

@RunWith(AndroidJUnit4::class)
class CreateHabitTest {

    private lateinit var createHabit: CreateHabit
    private lateinit var habitRepository: HabitRepository

    @Before
    fun beforeTest() {
        val appContext = InstrumentationRegistry.getTargetContext()
        val database = Room.inMemoryDatabaseBuilder(appContext, PlannerDatabase::class.java).allowMainThreadQueries().build()
        habitRepository = HabitRepository(database)
        createHabit = CreateHabit(habitRepository)
    }

    @Test
    fun createHabitTest() {
        // Context of the app under test.
        val createHabitParams = CreateHabit.CreateHabitParams(
                title = "testTitle",
                description = "testDescription",
                startDate = LocalDate.now(),
                alarmTime = LocalTime.now()
        )
        val habit = createHabit.execute(createHabitParams).blockingGet()
        val insertedHabit = habitRepository.getHabitByUUID(habit.uuid).blockingGet()
        Assert.assertEquals(createHabitParams.description, habit.description)
        Assert.assertEquals(createHabitParams.startDate, habit.startDate)
        Assert.assertEquals(createHabitParams.title, habit.title)
        Assert.assertEquals(createHabitParams.alarmTime, habit.alarmTime)
        Assert.assertEquals(insertedHabit.description, habit.description)
        Assert.assertEquals(insertedHabit.startDate, habit.startDate)
        Assert.assertEquals(insertedHabit.title, habit.title)
        Assert.assertEquals(insertedHabit.alarmTime, habit.alarmTime)
    }
}