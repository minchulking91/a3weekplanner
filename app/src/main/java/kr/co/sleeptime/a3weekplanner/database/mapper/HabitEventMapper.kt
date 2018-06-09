package kr.co.sleeptime.a3weekplanner.database.mapper

import kr.co.sleeptime.a3weekplanner.database.entity.HabitEventEntity
import kr.co.sleeptime.a3weekplanner.model.HabitEvent
import kr.co.sleeptime.a3weekplanner.model.value.HabitEventType
import org.threeten.bp.LocalDateTime

object HabitEventMapper {

    @Throws(Exception::class)
    fun toModel(todoEventEntity: HabitEventEntity): HabitEvent {
        val createdAt = LocalDateTime.parse(todoEventEntity.eventAt)
        return HabitEvent(
                uuid = todoEventEntity.uuid,
                habitUUID = todoEventEntity.habitUUID,
                eventAt = createdAt,
                memo = todoEventEntity.memo,
                eventType = HabitEventType.valueOf(todoEventEntity.eventType))
    }

    fun toEntity(todoEvent: HabitEvent): HabitEventEntity {
        return HabitEventEntity(
                uuid = todoEvent.uuid,
                habitUUID = todoEvent.habitUUID,
                eventAt = todoEvent.eventAt.toString(),
                memo = todoEvent.memo,
                eventType = todoEvent.eventType.toString())
    }

}