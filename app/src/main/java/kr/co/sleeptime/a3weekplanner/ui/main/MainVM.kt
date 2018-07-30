package kr.co.sleeptime.a3weekplanner.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kr.co.sleeptime.a3weekplanner.rx.SingleLiveEvent

class MainVM(app: Application) : AndroidViewModel(app) {

    val onEventCreateHabit = SingleLiveEvent<Any>()

    fun onClickCreateHabit() {
        onEventCreateHabit.call()
    }
}