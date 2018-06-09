package kr.co.sleeptime.a3weekplanner.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class HabitEntity(
        @PrimaryKey
        val uuid: String,
        @ColumnInfo(name = "start_date")
        val startDate: String,
        val title: String,
        val description: String? = null,
        @ColumnInfo(name = "created_at")
        val createdAt: String,
        @ColumnInfo(name = "is_canceled")
        val isCanceled: Boolean,
        @ColumnInfo(name = "alarm_date_time")
        val alarmTime: String? = null
)