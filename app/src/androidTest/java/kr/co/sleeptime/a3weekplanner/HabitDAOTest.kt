package kr.co.sleeptime.a3weekplanner

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.EmptyResultSetException
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions
import kr.co.sleeptime.a3weekplanner.database.PlannerDatabase
import kr.co.sleeptime.a3weekplanner.database.dao.HabitDAO
import kr.co.sleeptime.a3weekplanner.database.mapper.HabitMapper
import kr.co.sleeptime.a3weekplanner.model.Habit
import kr.co.sleeptime.a3weekplanner.model.value.HabitState
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

class HabitDAOTest {

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

    @Test
    fun insertAndLoadHabit() {
        val startDate = LocalDate.of(2018, 6, 11)
        val endDate = LocalDate.of(2018, 7, 2)
        val alarmTime = LocalTime.of(12, 25)
        val createHabit = Habit.createHabit("title", startDate, alarmTime, "test", true)
        habitDAO.insertHabit(HabitMapper.toEntity(createHabit))
        habitDAO.loadHabit(createHabit.uuid)
                .test()
                .assertValue {
                    try {
                        Preconditions.checkState(it.startDate == startDate)
                        Preconditions.checkState(it.endDate == endDate)
                        Preconditions.checkState(it.alarmTime == alarmTime)
                        Preconditions.checkState(it.title == "title")
                        Preconditions.checkState(it.description == "test")
                        Preconditions.checkState(it.isWeekdaysOnly)
                        true
                    } catch (e: Exception) {
                        e.printStackTrace()
                        false
                    }
                }
    }

    @Test
    fun deleteHabit() {
        val startDate = LocalDate.of(2018, 6, 11)
        val alarmTime = LocalTime.of(12, 25)
        val createHabit = Habit.createHabit("title", startDate, alarmTime, "test", true)
        habitDAO.insertHabit(HabitMapper.toEntity(createHabit))
        habitDAO.deleteHabit(HabitMapper.toEntity(createHabit))
        habitDAO.loadHabit(createHabit.uuid)
                .test()
                .assertError {
                    it is EmptyResultSetException
                }
    }

    @Test
    fun loadHabitsByDate() {
        val startDate = LocalDate.of(2018, 6, 11)
        val alarmTime = LocalTime.of(12, 25)
        val createHabit1 = Habit.createHabit("title", startDate, alarmTime, "test", true).let { HabitMapper.toEntity(it) }
        val createHabit2 = Habit.createHabit("title", startDate.plusDays(1), alarmTime, "test", true).let { HabitMapper.toEntity(it) }
        val createHabit3 = Habit.createHabit("title", startDate.plusDays(2), alarmTime, "test", true).let { HabitMapper.toEntity(it) }
        habitDAO.insertHabit(createHabit1, createHabit2, createHabit3)
        val queryDate = startDate.plusDays(1)
        habitDAO.loadHabitsByDate(queryDate)
                .test()
                .assertValue {
                    val habit = HabitMapper.toModel(it)
                    try {
                        Preconditions.checkState(habit.checkIsIn(queryDate))
                        true
                    } catch (e: Exception) {
                        e.printStackTrace()
                        false
                    }
                }
    }

    @Test
    fun loadActiveHabitsByDate() {
        val startDate = LocalDate.now()
        val alarmTime = LocalTime.of(12, 25)
        val createHabit1 = Habit.createHabit("title", startDate, alarmTime, "test", true).let { HabitMapper.toEntity(it) }
        val createHabit2 = Habit.createHabit("title", startDate.plusDays(1), alarmTime, "test", true).let { HabitMapper.toEntity(it) }
        val createHabit3 = Habit.createHabit("title", startDate.plusDays(2), alarmTime, "test", true).let { HabitMapper.toEntity(it) }.copy(canceledAt = startDate.atTime(alarmTime))
        habitDAO.insertHabit(createHabit1, createHabit2, createHabit3)
        val queryDate = startDate.plusDays(1)
        habitDAO.loadActiveHabitsByDate(queryDate)
                .test()
                .assertValue {
                    val habit = HabitMapper.toModel(it)
                    try {
                        Preconditions.checkState(habit.checkIsIn(queryDate))
                        Preconditions.checkState(!habit.isCanceled)
                        Preconditions.checkState(habit.state == HabitState.PROGRESS)
                        true
                    } catch (e: Exception) {
                        e.printStackTrace()
                        false
                    }
                }
    }

    @Test
    fun loadHabits() {
        val startDate = LocalDate.of(2018, 6, 11)
        val alarmTime = LocalTime.of(12, 25)
        val createHabit1 = Habit.createHabit("title", startDate, alarmTime, "test", true).let { HabitMapper.toEntity(it) }
        val createHabit2 = Habit.createHabit("title", startDate.plusDays(1), alarmTime, "test", true).let { HabitMapper.toEntity(it) }
        val createHabit3 = Habit.createHabit("title", startDate.plusDays(2), alarmTime, "test", true).let { HabitMapper.toEntity(it) }.copy(canceledAt = startDate.atTime(alarmTime))
        habitDAO.insertHabit(createHabit1, createHabit2, createHabit3)
        val queryDate = startDate.plusDays(1)
        habitDAO.loadHabitsByDate(queryDate)
                .test()
                .assertValue {
                    when {
                        it.uuid == createHabit1.uuid -> {
                            createHabit1 == it
                        }
                        it.uuid == createHabit2.uuid -> {
                            createHabit2 == it
                        }
                        it.uuid == createHabit3.uuid -> {
                            createHabit3 == it
                        }
                        else -> {
                            false
                        }
                    }
                }
    }

    @Test
    fun updateHabit() {
        val startDate = LocalDate.of(2018, 6, 11)
        val alarmTime = LocalTime.of(12, 25)
        val createHabit1 = Habit.createHabit("title", startDate, alarmTime, "test", true).let { HabitMapper.toEntity(it) }
        habitDAO.insertHabit(createHabit1)
        habitDAO.updateHabit(createHabit1.copy(description = "test2"))
        habitDAO.loadHabit(createHabit1.uuid)
                .test()
                .assertValue {
                    it.description == "test2"
                }
    }
}