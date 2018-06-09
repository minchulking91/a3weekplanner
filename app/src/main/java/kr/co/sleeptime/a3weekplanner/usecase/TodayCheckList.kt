package kr.co.sleeptime.a3weekplanner.usecase

import io.reactivex.Single
import kr.co.sleeptime.a3weekplanner.model.DailyHabitCheckState
import kr.co.sleeptime.a3weekplanner.model.value.HabitEventType
import kr.co.sleeptime.a3weekplanner.repository.HabitEventRepository
import kr.co.sleeptime.a3weekplanner.repository.HabitRepository
import org.threeten.bp.LocalDate

/**
 * 날짜별 체크 리스트 불러오기
 * 습관 목록에서 요청날짜에 진행중인 목록 가져오기.
 * 습관 이벤트에서 요청날짜 이벤트 가져오기.
 * 이벤트 상태 - 체크됨. 체크 안됨.
 */
class TodayCheckList(private val habitRepository: HabitRepository, private val habitEventRepository: HabitEventRepository) : UseCase<TodayCheckList.TodayCheckListParams, Single<List<DailyHabitCheckState>>> {
    override fun execute(params: TodayCheckListParams): Single<List<DailyHabitCheckState>> {
        return habitRepository.getHabitsByDate(params.queryDate)
                .flatMap { habits ->
                    habitEventRepository.getHabitEvents(params.queryDate, HabitEventType.CHECKED)
                            .map {
                                it.associateBy { it.habitUUID }
                            }
                            .map { habitEventMap ->
                                habits.map {
                                    val habitEvent = habitEventMap[it.uuid]
                                    DailyHabitCheckState.getCheckedEvent(it, habitEvent)
                                }
                            }
                }
    }

    data class TodayCheckListParams(
            val queryDate: LocalDate
    )
}