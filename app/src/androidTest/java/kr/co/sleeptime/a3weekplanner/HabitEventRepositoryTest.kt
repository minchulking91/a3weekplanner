package kr.co.sleeptime.a3weekplanner

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import kr.co.sleeptime.a3weekplanner.database.PlannerDatabase
import kr.co.sleeptime.a3weekplanner.repository.HabitEventRepository
import kr.co.sleeptime.a3weekplanner.repository.HabitRepository
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime

class HabitEventRepositoryTest {
    val instantTaskExecutorRule = InstantTaskExecutorRule()
        @Rule get

    private lateinit var habitRepository: HabitRepository
    private lateinit var habitEventRepository: HabitEventRepository
    private lateinit var database: PlannerDatabase

    @Before
    fun beforeTest() {
        val appContext = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(appContext, PlannerDatabase::class.java).allowMainThreadQueries().build()
        habitRepository = HabitRepository(database.habitDAO())
        habitEventRepository = HabitEventRepository(database.habitEventDAO())
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
    fun updateHabitMemo() {
        val title = "test"
        val startDate = LocalDate.now()
        val alarmTime = LocalTime.now()
        val description = "test description"
        val isWeekdaysOnly = true
        habitRepository.createHabit(title, startDate, alarmTime, description, isWeekdaysOnly)
                .flatMapObservable { habit ->
                    habitEventRepository.updateHabitMemo(habit, "newMemo", LocalDate.of(2018, 6, 17))
                            .flatMap {
                                habitEventRepository.updateHabitMemo(habit, "newMemo", LocalDate.of(2018, 6, 18))
                            }.flatMapObservable {
                                habitEventRepository.getMemosByHabit(habit).toObservable()
                            }
                }
                .test()
                .assertValue {
                    it.size == 2 &&
                            it.find {
                                !(it.memo == "newMemo" && (it.date == LocalDate.of(2018, 6, 17) || it.date == LocalDate.of(2018, 6, 18)))
                            } == null
                }
    }

    @Test
    fun insertHabitCheck() {
        val title = "test"
        val startDate = LocalDate.now()
        val alarmTime = LocalTime.now()
        val description = "test description"
        val isWeekdaysOnly = true
        habitRepository.createHabit(title, startDate, alarmTime, description, isWeekdaysOnly)
                .flatMap {
                    habitEventRepository.insertHabitCheck(it, LocalDateTime.now())
                }
                .test()
                .assertComplete()
    }

    @Test
    fun loadHabitCheckByDate() {
        val title = "test"
        val startDate = LocalDate.now()
        val description = "test description"
        val isWeekdaysOnly = true
        val checkedAt = LocalDateTime.now()
        habitRepository.createHabit(title, startDate, null, description, isWeekdaysOnly)
                .flatMap { habit ->
                    habitEventRepository.insertHabitCheck(habit, checkedAt)
                            .flatMap {
                                habitEventRepository.insertHabitCheck(habit, checkedAt.plusDays(1))
                            }
                }
                .flatMapObservable {
                    habitEventRepository.habitChecksByDate(LocalDate.now()).toObservable()
                }
                .test()
                .assertValue {
                    it.size == 1 &&
                            it.find {
                                it.checkedAt != checkedAt
                            } == null
                }
    }

    @Test
    fun loadHabitCheckByHabit() {
        val title = "test"
        val startDate = LocalDate.now()
        val description = "test description"
        val isWeekdaysOnly = true
        val checkedAt = LocalDateTime.now()
        habitRepository.createHabit(title, startDate, null, description, isWeekdaysOnly)
                .flatMapObservable { habit ->
                    habitEventRepository.insertHabitCheck(habit, checkedAt)
                            .flatMap {
                                habitEventRepository.insertHabitCheck(habit, checkedAt.plusDays(1))
                            }
                            .flatMapObservable {
                                habitEventRepository.habitChecksByHabit(habit).toObservable()
                            }
                }
                .test()
                .assertValue {
                    it.size == 2 &&
                            it.find {
                                !(it.checkedAt == checkedAt || it.checkedAt == checkedAt.plusDays(1))
                            } == null
                }
    }
}