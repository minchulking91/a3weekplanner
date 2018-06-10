package kr.co.sleeptime.a3weekplanner.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime

@Entity(tableName = "habits")
data class HabitEntity(
        @PrimaryKey
        val uuid: String,
        @ColumnInfo(name = "start_date")
        val startDate: LocalDate,
        @ColumnInfo(name = "end_date")
        val endDate: LocalDate,
        val title: String,
        val description: String? = null,
        @ColumnInfo(name = "created_at")
        val createdAt: LocalDateTime,
        @ColumnInfo(name = "canceled_at")
        val canceledAt: LocalDateTime?,
        @ColumnInfo(name = "alarm_time")
        val alarmTime: LocalTime? = null,
        @ColumnInfo(name = "is_weekdays_only")
        val isWeekdaysOnly: Boolean
)