package kr.co.sleeptime.a3weekplanner.database.mapper

import androidx.room.TypeConverter
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class TimestampConverter {

    @TypeConverter
    fun fromLocalDateString(value: String?): LocalDate? {
        return try {
            LocalDate.parse(value)
        } catch (e: Exception) {
            null
        }
    }

    @TypeConverter
    fun toLocalDateString(value: LocalDate?): String? {
        return try {
            value?.toString()
        } catch (e: Exception) {
            null
        }
    }

}