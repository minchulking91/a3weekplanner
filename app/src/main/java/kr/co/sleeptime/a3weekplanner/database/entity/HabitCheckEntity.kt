package kr.co.sleeptime.a3weekplanner.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import org.threeten.bp.LocalDateTime

@Entity(tableName = "habit_events",
        primaryKeys = ["uuid", "checked_at"],
        indices = [(Index(value = ["uuid"]))],
        foreignKeys = [(ForeignKey(entity = HabitEntity::class, parentColumns = ["uuid"], childColumns = ["uuid"], onDelete = CASCADE))])
data class HabitCheckEntity(
        val uuid: String,
        @ColumnInfo(name = "checked_at")
        val checkedAt: LocalDateTime
)