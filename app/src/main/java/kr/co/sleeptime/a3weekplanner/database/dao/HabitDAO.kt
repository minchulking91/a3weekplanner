package kr.co.sleeptime.a3weekplanner.database.dao

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single
import kr.co.sleeptime.a3weekplanner.database.entity.HabitEntity
import kr.co.sleeptime.a3weekplanner.database.entity.HabitEventEntity

@Dao
interface HabitDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHabit(vararg todoEntity: HabitEntity)

    @Delete
    fun deleteHabit(vararg todoEntity: HabitEntity)

    @Query("SELECT * FROM habits")
    fun loadHabits(): List<HabitEntity>

    @Query("SELECT * FROM habits WHERE :date BETWEEN date(start_date) AND date(start_date, '+21 day')")
    fun loadHabitsByDate(date: String): List<HabitEntity>

    @Query("SELECT * FROM habits WHERE uuid = :uuid LIMIT 1")
    fun loadHabit(uuid: String): HabitEntity

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateHabit(todoEntity: HabitEntity)
}

