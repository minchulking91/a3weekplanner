package kr.co.sleeptime.a3weekplanner.database

import androidx.room.TypeConverter
import org.threeten.bp.*


class Converters {
    @TypeConverter
    fun timeStampToLocalDateTime(value: Long?): LocalDateTime? {
        return value?.let { Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDateTime() }
    }

    @TypeConverter
    fun localDateTimeToTimeStamp(date: LocalDateTime?): Long? {
        return date?.atZone(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()
    }

    @TypeConverter
    fun timeStampToLocalDate(value: Long?): LocalDate? {
        return value?.let { Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate() }
    }

    @TypeConverter
    fun localDateToTimeStamp(date: LocalDate?): Long? {
        return date?.atTime(0, 0)?.atZone(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()
    }
    @TypeConverter
    fun timeStampToLocalTime(value: Long?): LocalTime? {
        return value?.let { LocalTime.ofSecondOfDay(it) }
    }

    @TypeConverter
    fun localTimeToTimeStamp(date: LocalTime?): Long? {
        return date?.toSecondOfDay()?.toLong()
    }
}