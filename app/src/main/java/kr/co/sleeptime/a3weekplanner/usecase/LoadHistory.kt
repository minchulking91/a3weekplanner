package kr.co.sleeptime.a3weekplanner.usecase

import io.reactivex.Single
import kr.co.sleeptime.a3weekplanner.model.HabitHistory
import kr.co.sleeptime.a3weekplanner.model.value.HabitEventType
import kr.co.sleeptime.a3weekplanner.repository.HabitEventRepository

/**
 * 습관별 기록 보기
 * 언제 생성되었는지, 언제 체크했는지, 언제 체크가 안되었는지, 언제 취소 되었는지.
 * state [HabitEventType]
 */
class LoadHistory(private val habitEventRepository: HabitEventRepository) : UseCase<LoadHistory.HistoryParams, Single<List<HabitHistory>>> {
    override fun execute(params: HistoryParams): Single<List<HabitHistory>> {
        habitEventRepository.getHabitEvents(params.uuid)
                .map {
                    HabitHistory.fromHabitEvent(it)
                }
        return Single.error(NotImplementedError())
    }

    data class HistoryParams(
            val uuid: String //habit UUID
    )
}