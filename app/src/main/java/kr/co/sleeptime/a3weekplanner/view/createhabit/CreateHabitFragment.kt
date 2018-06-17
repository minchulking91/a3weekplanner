package kr.co.sleeptime.a3weekplanner.view.createhabit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import kr.co.sleeptime.a3weekplanner.R
import kr.co.sleeptime.a3weekplanner.databinding.FragmentCreateHabitBinding
import kr.co.sleeptime.a3weekplanner.view.base.BaseBottomSheetFragment


class CreateHabitFragment : BaseBottomSheetFragment() {
    override val layoutRes: Int = R.layout.fragment_create_habit
    override val isUseDataBinding: Boolean = true
    private val createHabitVM: CreateHabitVM by lazy { ViewModelProviders.of(this).get(CreateHabitVM::class.java) }
    private lateinit var binding: FragmentCreateHabitBinding

    override fun onDataBinding(inflater: LayoutInflater, container: ViewGroup?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.createHabitVM = createHabitVM
        binding.setLifecycleOwner(this)
        return binding.root
    }

    companion object {
        fun newInstance(): CreateHabitFragment {
            return CreateHabitFragment()
        }
    }
}