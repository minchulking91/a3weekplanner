package kr.co.sleeptime.a3weekplanner.database.mapper

import kr.co.sleeptime.a3weekplanner.database.entity.HabitCheckEntity
import kr.co.sleeptime.a3weekplanner.database.entity.HabitMemoEntity
import kr.co.sleeptime.a3weekplanner.model.HabitCheck
import kr.co.sleeptime.a3weekplanner.model.HabitMemo

object HabitEventMapper {

    fun toEntity(habitMemo: HabitMemo): HabitMemoEntity {
        return HabitMemoEntity(habitMemo.uuid, habitMemo.memo, habitMemo.date)
    }

    fun toModel(habitMemoEntity: HabitMemoEntity): HabitMemo {
        return HabitMemo(habitMemoEntity.uuid, habitMemoEntity.memo, habitMemoEntity.date)
    }

    fun toMemoModelList(habitMemoEntities: List<HabitMemoEntity>): List<HabitMemo> {
        return habitMemoEntities.map { toModel(it) }
    }

    fun toEntity(habitCheck: HabitCheck): HabitCheckEntity {
        return HabitCheckEntity(habitCheck.uuid, habitCheck.checkedAt)
    }

    fun toModel(habitCheckEntity: HabitCheckEntity): HabitCheck {
        return HabitCheck(habitCheckEntity.uuid, habitCheckEntity.checkedAt)
    }

    fun toCheckModelList(habitCheckEntities: List<HabitCheckEntity>): List<HabitCheck> {
        return habitCheckEntities.map { toModel(it) }
    }
}