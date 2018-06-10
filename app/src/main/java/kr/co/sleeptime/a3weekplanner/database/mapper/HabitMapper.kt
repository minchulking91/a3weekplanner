package kr.co.sleeptime.a3weekplanner.database.mapper

import kr.co.sleeptime.a3weekplanner.database.entity.HabitEntity
import kr.co.sleeptime.a3weekplanner.model.Habit

object HabitMapper {

    fun toEntity(todo: Habit): HabitEntity {
        return HabitEntity(
                uuid = todo.uuid,
                startDate = todo.startDate,
                endDate = todo.endDate,
                title = todo.title,
                description = todo.description,
                createdAt = todo.createdAt,
                canceledAt = todo.canceledAt,
                alarmTime = todo.alarmTime,
                isWeekdaysOnly = todo.isWeekdaysOnly)
    }

    @Throws(Exception::class)
    fun toModel(todoEntity: HabitEntity): Habit {
        return Habit(
                uuid = todoEntity.uuid,
                startDate = todoEntity.startDate,
                endDate = todoEntity.endDate,
                title = todoEntity.title,
                description = todoEntity.description,
                createdAt = todoEntity.createdAt,
                canceledAt = todoEntity.canceledAt,
                alarmTime = todoEntity.alarmTime,
                isWeekdaysOnly = todoEntity.isWeekdaysOnly)
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