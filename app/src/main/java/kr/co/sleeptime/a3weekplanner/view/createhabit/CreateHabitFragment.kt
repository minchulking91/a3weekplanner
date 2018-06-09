package kr.co.sleeptime.a3weekplanner.view.createhabit

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import kr.co.sleeptime.a3weekplanner.R
import kr.co.sleeptime.a3weekplanner.view.base.BaseBottomSheetFragment

class CreateHabitFragment : BaseBottomSheetFragment() {
    override val layoutRes: Int = R.layout.fragment_create_habit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    companion object {
        fun newInstance(): CreateHabitFragment {
            return CreateHabitFragment()
        }
    }
}