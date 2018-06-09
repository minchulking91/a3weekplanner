package kr.co.sleeptime.a3weekplanner.database.mapper

import kr.co.sleeptime.a3weekplanner.database.entity.HabitEntity
import kr.co.sleeptime.a3weekplanner.model.Habit
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

object HabitMapper {

    fun toEntity(todo: Habit): HabitEntity {
        return HabitEntity(
                uuid = todo.uuid,
                startDate = todo.startDate.toString(),
                title = todo.title,
                description = todo.description,
                createdAt = todo.createdAt.toString(),
                isCanceled = todo.isCanceled,
                alarmTime = todo.alarmTime?.toString())
    }

    @Throws(Exception::class)
    fun toModel(todoEntity: HabitEntity): Habit {
        val startDate = LocalDate.parse(todoEntity.startDate)
        val alarmTime: LocalTime? = try {
            LocalTime.parse(todoEntity.alarmTime)
        } catch (e: Exception) {
            null
        }
        return Habit(
                uuid = todoEntity.uuid,
                startDate = startDate,
                title = todoEntity.title,
                description = todoEntity.description,
                createdAt = Instant.parse(todoEntity.createdAt),
                isCanceled = todoEntity.isCanceled,
                alarmTime = alarmTime)
    }

    fun toModel(entities: List<HabitEntity>?): List<Habit> {
        return entities?.mapNotNull {
            try {
                toModel(it)
            } catch (e: Exception) {
                null
            }
        } ?: emptyList()
    }

}