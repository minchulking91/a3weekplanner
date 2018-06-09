package kr.co.sleeptime.a3weekplanner.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kr.co.sleeptime.a3weekplanner.database.dao.HabitEventDAO
import kr.co.sleeptime.a3weekplanner.database.dao.HabitDAO
import kr.co.sleeptime.a3weekplanner.database.entity.HabitEventEntity
import kr.co.sleeptime.a3weekplanner.database.entity.HabitEntity
import kr.co.sleeptime.a3weekplanner.database.mapper.TimestampConverter

@Database(entities = [HabitEntity::class, HabitEventEntity::class], version = 1,exportSchema = false)
@TypeConverters(TimestampConverter::class)
abstract class PlannerDatabase : RoomDatabase() {
    abstract fun habitDAO(): HabitDAO
    abstract fun habitEventDAO(): HabitEventDAO
}