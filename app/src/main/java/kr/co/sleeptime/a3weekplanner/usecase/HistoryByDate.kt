package kr.co.sleeptime.a3weekplanner.usecase

import io.reactivex.Single
import kr.co.sleeptime.a3weekplanner.model.HabitEvent
import org.threeten.bp.LocalDate

/**
 * 날짜별 내역 보기
 */
class HistoryByDate : UseCase<HistoryByDate.HistoryByDateParams, Single<List<HabitEvent>>> {
    override fun execute(params: HistoryByDateParams): Single<List<HabitEvent>> {
        return Single.error(NotImplementedError())
    }

    data class HistoryByDateParams(
            val queryDate: LocalDate
    )
}