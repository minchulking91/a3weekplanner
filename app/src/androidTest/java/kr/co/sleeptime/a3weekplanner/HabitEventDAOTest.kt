package kr.co.sleeptime.a3weekplanner

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import kr.co.sleeptime.a3weekplanner.database.PlannerDatabase
import kr.co.sleeptime.a3weekplanner.database.dao.HabitDAO
import kr.co.sleeptime.a3weekplanner.database.dao.HabitEventDAO
import kr.co.sleeptime.a3weekplanner.database.mapper.HabitEventMapper
import kr.co.sleeptime.a3weekplanner.database.mapper.HabitMapper
import kr.co.sleeptime.a3weekplanner.model.Habit
import kr.co.sleeptime.a3weekplanner.model.HabitCheck
import kr.co.sleeptime.a3weekplanner.model.HabitMemo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime

class HabitEventDAOTest {

    lateinit var habitDAO: HabitDAO
    lateinit var habitEventDAO: HabitEventDAO
    lateinit var database: PlannerDatabase

    val instantTaskExecutorRule = InstantTaskExecutorRule()
        @Rule get

    @Before
    fun beforeTest() {
        val appContext = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(appContext, PlannerDatabase::class.java).allowMainThreadQueries().build()
        habitDAO = database.habitDAO()
        habitEventDAO = database.habitEventDAO()
    }

    @After
    fun afterTest() {
        database.close()
    }

    @Test
    fun insertHabitMemo() {
        val startDate = LocalDate.of(2018, 6, 11)
        val alarmTime = LocalTime.of(12, 25)
        val createHabit1 = Habit.createHabit("title", startDate, alarmTime, "test", true).let { HabitMapper.toEntity(it) }
        val habitMemoEntity = HabitMemo(createHabit1.uuid, "memo", LocalDate.now()).let { HabitEventMapper.toEntity(it) }
        habitDAO.insertHabit(createHabit1)
        habitEventDAO.insertHabitMemo(habitMemoEntity)
        habitEventDAO.loadHabitMemosByUUID(createHabit1.uuid)
                .test()
                .assertValue {
                    it.memo == "memo" && it.date == LocalDate.now()
                }
    }

    @Test
    fun updateHabitMemo() {
        val startDate = LocalDate.of(2018, 6, 11)
        val alarmTime = LocalTime.of(12, 25)
        val createHabit1 = Habit.createHabit("title", startDate, alarmTime, "test", true).let { HabitMapper.toEntity(it) }
        val habitMemoEntity = HabitMemo(createHabit1.uuid, "memo", LocalDate.now()).let { HabitEventMapper.toEntity(it) }
        habitDAO.insertHabit(createHabit1)
        habitEventDAO.insertHabitMemo(habitMemoEntity)
        habitEventDAO.insertHabitMemo(habitMemoEntity.copy(memo = "memo modified", date = LocalDate.now()))
        habitEventDAO.loadHabitMemosByUUID(createHabit1.uuid)
                .test()
                .assertValue {
                    it.memo == "memo modified" && it.date == LocalDate.now()
                }
    }

    @Test
    fun loadHabitMemoByDate() {
        val startDate = LocalDate.of(2018, 6, 11)
        val alarmTime = LocalTime.of(12, 25)
        val createHabit1 = Habit.createHabit("title", startDate, alarmTime, "test", true).let { HabitMapper.toEntity(it) }
        val habitMemoEntity = HabitMemo(createHabit1.uuid, "memo", LocalDate.now()).let { HabitEventMapper.toEntity(it) }
        habitDAO.insertHabit(createHabit1)
        habitEventDAO.insertHabitMemo(habitMemoEntity)
        habitEventDAO.loadHabitMemosByDate(LocalDate.now())
                .test()
                .assertValue {
                    it.memo == "memo" && it.date == LocalDate.now()
                }
    }

    @Test
    fun loadHabitCheckByDate() {
        val startDate = LocalDate.of(2018, 6, 11)
        val alarmTime = LocalTime.of(12, 25)
        val createHabit1 = Habit.createHabit("title", startDate, alarmTime, "test", true).let { HabitMapper.toEntity(it) }
        val habitCheckEntity = HabitCheck(createHabit1.uuid, LocalDateTime.now()).let { HabitEventMapper.toEntity(it) }
        habitDAO.insertHabit(createHabit1)
        habitEventDAO.insertHabitCheck(habitCheckEntity)
        val queryDate = LocalDate.now()
        habitEventDAO.loadHabitChecksByDaysBetween(queryDate, queryDate.plusDays(1))
                .test()
                .assertValue {
                    it.uuid == createHabit1.uuid && it.checkedAt.toLocalDate() == LocalDate.now()
                }
    }
}