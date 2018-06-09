package kr.co.sleeptime.a3weekplanner

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import kr.co.sleeptime.a3weekplanner.database.PlannerDatabase
import kr.co.sleeptime.a3weekplanner.model.DailyHabitCheckState
import kr.co.sleeptime.a3weekplanner.model.Habit
import kr.co.sleeptime.a3weekplanner.repository.HabitEventRepository
import kr.co.sleeptime.a3weekplanner.repository.HabitRepository
import kr.co.sleeptime.a3weekplanner.usecase.CheckHabit
import kr.co.sleeptime.a3weekplanner.usecase.CreateHabit
import kr.co.sleeptime.a3weekplanner.usecase.TodayCheckList
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

@RunWith(AndroidJUnit4::class)
class DailyListUseCaseTest {

    private lateinit var createHabit: CreateHabit
    private lateinit var todayCheckList: TodayCheckList
    private lateinit var checkHabit: CheckHabit
    private lateinit var habitRepository: HabitRepository
    private lateinit var habitEventRepository: HabitEventRepository

    @Before
    fun beforeTest() {
        val appContext = InstrumentationRegistry.getTargetContext()
        val database = Room.inMemoryDatabaseBuilder(appContext, PlannerDatabase::class.java).allowMainThreadQueries().build()
        habitRepository = HabitRepository(database)
        habitEventRepository = HabitEventRepository(database)
        createHabit = CreateHabit(habitRepository)
        todayCheckList = TodayCheckList(habitRepository, habitEventRepository)
        checkHabit = CheckHabit(habitEventRepository)
    }

    @Test
    fun dailyListTest() {
        createHabit(title = "testTitle", description = "testDescription", startDate = LocalDate.now(), alarmTime = null)
        createHabit(title = "testTitle2", description = "testDescription2", startDate = LocalDate.now(), alarmTime = null)
        val checkListByDateParams = TodayCheckList.TodayCheckListParams(LocalDate.now())
        val dailyEvent: List<DailyHabitCheckState> = todayCheckList.execute(checkListByDateParams).blockingGet()
        assert(dailyEvent.size == 2)
        assert(!dailyEvent[0].isChecked)
        assert(!dailyEvent[1].isChecked)
    }

    @Test
    fun checkDailyListTest() {
        val habit = createHabit(title = "testTitle", description = "testDescription", startDate = LocalDate.now(), alarmTime = null)
        val dailyEvents: List<DailyHabitCheckState> = todayCheckList.execute(TodayCheckList.TodayCheckListParams(LocalDate.now())).blockingGet()
        val checkHabitParams = CheckHabit.CheckHabitParams(dailyEvents.first(), "testMemo")
        val throwable = checkHabit.execute(checkHabitParams).blockingGet()
        assert(throwable == null)
        val todayCheckList = todayCheckList.execute(TodayCheckList.TodayCheckListParams(LocalDate.now())).blockingGet()
        val first = todayCheckList.first()
        assert(first.isChecked)
        assert(first.memo.equals("testMemo"))
    }

    private fun createHabit(title: String, description: String?, startDate: LocalDate, alarmTime: LocalTime?): Habit {
        val createHabitParams = CreateHabit.CreateHabitParams(
                title = title,
                description = description,
                startDate = startDate,
                alarmTime = alarmTime
        )
        return createHabit.execute(createHabitParams).blockingGet()
    }
}