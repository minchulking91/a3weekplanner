package kr.co.sleeptime.a3weekplanner

import androidx.room.EmptyResultSetException
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import kr.co.sleeptime.a3weekplanner.database.PlannerDatabase
import kr.co.sleeptime.a3weekplanner.repository.HabitRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.threeten.bp.LocalDate

@RunWith(AndroidJUnit4::class)
class HabitRepositoryTest {

    private lateinit var todoRepository: HabitRepository

    @Before
    fun beforeTest() {
        val appContext = InstrumentationRegistry.getTargetContext()
        val database = Room.inMemoryDatabaseBuilder(appContext, PlannerDatabase::class.java).allowMainThreadQueries().build()
        todoRepository = HabitRepository(database)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("kr.co.sleeptime.a3weekplanner", appContext.packageName)
    }

    @Test
    fun insertHabit() {
        // Context of the app under test.
        val description = "testDescription"
        val title = "testTitle"
        val startDate = LocalDate.now()
        val todo = todoRepository.createHabit(title, startDate, null, description).blockingGet()
        val insertedHabit = todoRepository.getHabitByUUID(todo.uuid).blockingGet()

        Assert.assertEquals(description, todo.description)
        Assert.assertEquals(startDate, todo.startDate)
        Assert.assertEquals(title, todo.title)

        Assert.assertEquals(insertedHabit.description, todo.description)
        Assert.assertEquals(insertedHabit.startDate, todo.startDate)
        Assert.assertEquals(insertedHabit.title, todo.title)
    }

    @Test
    fun deleteHabit() {
        // Context of the app under test.
        val description = "testDescription"
        val title = "testTitle"
        val startDate = LocalDate.now()
        val todo = todoRepository.createHabit(title, startDate, null, description).blockingGet()

        todoRepository.deleteHabit(todo).blockingAwait()
        try {
            val insertedHabit = todoRepository.getHabitByUUID(todo.uuid).blockingGet()
        } catch (e: Exception) {
            Assert.assertEquals(e is EmptyResultSetException, true)
        }
    }



}