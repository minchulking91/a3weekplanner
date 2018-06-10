package kr.co.sleeptime.a3weekplanner.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import org.threeten.bp.LocalDate

@Entity(tableName = "habit_memos",
        primaryKeys = ["uuid", "date"],
        indices = [(Index(value = ["uuid"]))],
        foreignKeys = [(ForeignKey(entity = HabitEntity::class, parentColumns = ["uuid"], childColumns = ["uuid"], onDelete = ForeignKey.CASCADE))]
)
data class HabitMemoEntity(
        val uuid: String,
        val memo: String,
        val date: LocalDate
)