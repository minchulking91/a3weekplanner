package kr.co.sleeptime.a3weekplanner.database.dao

import androidx.room.*
import io.reactivex.Single
import kr.co.sleeptime.a3weekplanner.database.entity.HabitCheckEntity
import kr.co.sleeptime.a3weekplanner.database.entity.HabitMemoEntity
import org.threeten.bp.LocalDate

@Dao
interface HabitEventDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHabitMemo(vararg habitMemoEntity: HabitMemoEntity)

    @Delete
    fun deleteHabitMemo(vararg habitMemoEntity: HabitMemoEntity)

    @Query("SELECT * FROM habit_memos WHERE uuid = :uuid")
    fun loadHabitMemosByUUID(uuid: String): Single<List<HabitMemoEntity>>

    @Query("SELECT * FROM habit_memos WHERE date= :localDate")
    fun loadHabitMemosByDate(localDate: LocalDate): Single<List<HabitMemoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHabitCheck(vararg habitCheckEntity: HabitCheckEntity)

    @Delete
    fun deleteHabitCheck(vararg habitCheckEntity: HabitCheckEntity)

    @Query("SELECT * FROM habit_events WHERE uuid = :uuid")
    fun loadHabitChecksByUUID(uuid: String): Single<List<HabitCheckEntity>>

    @Query("SELECT * FROM habit_events WHERE checked_at > :startDate AND checked_at < :endDate")
    fun loadHabitChecksByDaysBetween(startDate: LocalDate, endDate: LocalDate): Single<List<HabitCheckEntity>>

}