package kr.co.sleeptime.a3weekplanner.database.dao

import androidx.room.*
import io.reactivex.Flowable
import kr.co.sleeptime.a3weekplanner.database.entity.HabitEventEntity

@Dao
interface HabitEventDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHabitEvent(todoEventEntity: HabitEventEntity)

    @Delete
    fun deleteHabitEvent(vararg todoEventEntity: HabitEventEntity)

    @Query("SELECT * FROM habit_events WHERE habit_uuid == :habitUUID")
    fun loadHabitEvent(habitUUID: String): Flowable<HabitEventEntity>

    @Query("SELECT * FROM habit_events WHERE created_at BETWEEN date(:queryDate) AND date(:queryDate, '+1 day')")
    fun loadHabitEventsByDate(queryDate: String): List<HabitEventEntity>

    @Query("SELECT * FROM habit_events WHERE (created_at BETWEEN date(:queryDate) AND date(:queryDate, '+1 day')) AND event_type = :habitEventType")
    fun loadHabitEvents(queryDate: String, habitEventType: String): List<HabitEventEntity>

}