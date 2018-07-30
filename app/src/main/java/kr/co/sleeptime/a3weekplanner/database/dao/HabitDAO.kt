package kr.co.sleeptime.a3weekplanner.database.dao

import androidx.room.*
import io.reactivex.Single
import kr.co.sleeptime.a3weekplanner.database.entity.HabitEntity
import org.threeten.bp.LocalDate

@Dao
interface HabitDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHabit(vararg habitEntity: HabitEntity)

    @Delete
    fun deleteHabit(vararg habitEntity: HabitEntity)

    @Query("SELECT * FROM habits")
    fun loadHabits(): Single<List<HabitEntity>>

    @Query("SELECT * FROM habits WHERE :date BETWEEN start_date AND end_date")
    fun loadHabitsByDate(date: LocalDate): Single<List<HabitEntity>>

    @Query("SELECT * FROM habits WHERE (:date BETWEEN start_date AND end_date) AND canceled_at IS NULL")
    fun loadActiveHabitsByDate(date: LocalDate): Single<List<HabitEntity>>

    @Query("SELECT * FROM habits WHERE uuid = :uuid LIMIT 1")
    fun loadHabit(uuid: String): Single<HabitEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateHabit(todoEntity: HabitEntity)
}

