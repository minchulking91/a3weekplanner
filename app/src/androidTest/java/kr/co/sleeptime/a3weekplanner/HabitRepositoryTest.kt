package kr.co.sleeptime.a3weekplanner

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import kr.co.sleeptime.a3weekplanner.database.PlannerDatabase
import kr.co.sleeptime.a3weekplanner.model.value.HabitState
import kr.co.sleeptime.a3weekplanner.repository.HabitRepository
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime

class HabitRepositoryTest {
    val instantTaskExecutorRule = InstantTaskExecutorRule()
        @Rule get

    private lateinit var habitRepository: HabitRepository
    private lateinit var database: PlannerDatabase

    @Before
    fun beforeTest() {
        val appContext = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(appContext, PlannerDatabase::class.java).allowMainThreadQueries().build()
        habitRepository = HabitRepository(database.habitDAO())
    }

    @After
    fun afterTest() {
        database.close()
    }

    @Test
    fun createHabit() {
        val title = "test"
        val startDate = LocalDate.now()
        val alarmTime = LocalTime.now()
        val description = "test description"
        val isWeekdaysOnly = true
        habitRepository.createHabit(title, startDate, alarmTime, description, isWeekdaysOnly)
                .test()
                .assertValue {
                    it.title == "test" && it.description == "test description"
                }
    }

    @Test
    fun loadHabit() {
        val title = "test"
        val startDate = LocalDate.now()
        val alarmTime = LocalTime.now()
        val description = "test description"
        val isWeekdaysOnly = true
        habitRepository.createHabit(title, startDate, alarmTime, description, isWeekdaysOnly)
                .flatMap {
                    habitRepository.createHabit("dummy", startDate.plusDays(1), alarmTime, description, isWeekdaysOnly)
                }
                .flatMapObservable {
                    habitRepository.loadByDate(LocalDate.now()).toObservable()
                }
                .test()
                .assertValueCount(1)
                .assertValue {
                    it.size == 1 &&
                            it.find {
                                !(it.title == "test" && it.description == "test description")
                            } == null
                }
    }

    @Test
    fun cancelHabit() {
        val title = "test"
        val startDate = LocalDate.now()
        val alarmTime = LocalTime.now()
        val description = "test description"
        val isWeekdaysOnly = true
        habitRepository.createHabit(title, startDate, alarmTime, description, isWeekdaysOnly)
                .flatMap {
                    habitRepository.cancelHabit(it, LocalDateTime.now())
                }
                .test()
                .assertValue {
                    it.getState(LocalDate.now()) == HabitState.CANCELED
                }
    }

    @Test
    fun updateAlarm() {
        val title = "test"
        val startDate = LocalDate.now()
        val alarmTime = LocalTime.now()
        val description = "test description"
        val isWeekdaysOnly = true
        habitRepository.createHabit(title, startDate, null, description, isWeekdaysOnly)
                .flatMap {

                    habitRepository.updateAlarm(it, alarmTime)
                }
                .test()
                .assertValue {
                    it.alarmTime == alarmTime
                }
    }
}