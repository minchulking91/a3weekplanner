package kr.co.sleeptime.a3weekplanner.usecase

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

class LoadTodayHabitsTest {


    lateinit var database: PlannerDatabase

    val instantTaskExecutorRule = InstantTaskExecutorRule()
        @Rule get

    private lateinit var loadTodayHabits: LoadTodayHabits

    private lateinit var habitRepository: HabitRepository

    private lateinit var habitEventRepository: HabitEventRepository

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(appContext, PlannerDatabase::class.java).allowMainThreadQueries().build()
        val habitDAO = database.habitDAO()
        val habitEventDAO = database.habitEventDAO()
        habitRepository = HabitRepository(habitDAO)
        habitEventRepository = HabitEventRepository(habitEventDAO)
        loadTodayHabits = LoadTodayHabits(habitRepository, habitEventRepository)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun executeEmpty() {
        loadTodayHabits.execute(LoadTodayHabits.LoadTodayHabitsParams(LocalDate.now()))
                .test()
                .assertValue {
                    it.isEmpty()
                }
    }

    @Test
    fun executeEmptyEventAndMemo() {
        habitRepository.createHabit("test", LocalDate.now(), null, "test", true)
                .flatMap {
                    loadTodayHabits.execute(LoadTodayHabits.LoadTodayHabitsParams(LocalDate.now()))
                }
                .test()
                .assertValue {
                    val first = it.first()
                    it.size == 1 && first.habitCheck == null && first.memo == null
                }
    }

    @Test
    fun executeEvent() {
        habitRepository.createHabit("test", LocalDate.now(), null, "test", true)
                .flatMap {
                    habitEventRepository.insertHabitCheck(it, LocalDateTime.now())
                }
                .flatMap {
                    loadTodayHabits.execute(LoadTodayHabits.LoadTodayHabitsParams(LocalDate.now()))
                }
                .test()
                .assertValue {
                    val first = it.first()
                    it.size == 1 && first.habitCheck != null && first.memo == null
                }
    }

    @Test
    fun executeMemo() {
        habitRepository.createHabit("test", LocalDate.now(), null, "test", true)
                .flatMap {
                    habitEventRepository.updateHabitMemo(it, "memo", LocalDate.now())
                }
                .flatMap {
                    loadTodayHabits.execute(LoadTodayHabits.LoadTodayHabitsParams(LocalDate.now()))
                }
                .test()
                .assertValue {
                    val first = it.first()
                    it.size == 1 && first.habitCheck == null && first.memo != null
                }
    }

    @Test
    fun executeMemoAnotherDay() {
        habitRepository.createHabit("test", LocalDate.now(), null, "test", true)
                .flatMap {
                    habitEventRepository.updateHabitMemo(it, "memo", LocalDate.now().plusDays(1))
                }
                .flatMap {
                    loadTodayHabits.execute(LoadTodayHabits.LoadTodayHabitsParams(LocalDate.now()))
                }
                .test()
                .assertValue {
                    val first = it.first()
                    it.size == 1 && first.habitCheck == null && first.memo == null
                }
    }

    @Test
    fun executeQueryAnotherDay() {
        habitRepository.createHabit("test", LocalDate.now(), null, "test", true)
                .flatMap {
                    habitEventRepository.updateHabitMemo(it, "memo", LocalDate.now().plusDays(1))
                }
                .flatMap {
                    loadTodayHabits.execute(LoadTodayHabits.LoadTodayHabitsParams(LocalDate.now().minusDays(1)))
                }
                .test()
                .assertValue {
                    it.isEmpty()
                }
    }
}