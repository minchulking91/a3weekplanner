package kr.co.sleeptime.a3weekplanner.database.entity

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "habit_events", indices = [Index(value = ["habit_uuid"])], foreignKeys = [(ForeignKey(entity = HabitEntity::class, parentColumns = ["uuid"], childColumns = ["habit_uuid"], onDelete = CASCADE))])
data class HabitEventEntity(
        @PrimaryKey
        val uuid: String,
        @ColumnInfo(name = "habit_uuid")
        val habitUUID: String,
        @ColumnInfo(name = "event_at")
        val eventAt: String,
        @ColumnInfo(name = "event_type")
        val eventType: String,
        val memo: String? = null
)