package kr.co.sleeptime.a3weekplanner.view.main

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.sleeptime.a3weekplanner.R
import kr.co.sleeptime.a3weekplanner.databinding.ActivityMainBinding
import kr.co.sleeptime.a3weekplanner.view.base.BaseActivity
import kr.co.sleeptime.a3weekplanner.view.createhabit.CreateHabitFragment


class MainActivity : BaseActivity() {


    override val layoutRes: Int = R.layout.activity_main
    override val isUseDataBinding: Boolean = true
    private lateinit var binding:ActivityMainBinding
    private val mainVM: MainVM by lazy { ViewModelProviders.of(this).get(MainVM::class.java) }
    private val navigationFragment: MainBottomNavigationFragment by lazy { MainBottomNavigationFragment.newInstance() }

    override fun onDataBinding() {
        super.onDataBinding()
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.mainVM = mainVM
    }

    override fun setupViews() {
        bottom_app_bar.setNavigationOnClickListener {
            navigationFragment.show(supportFragmentManager, "main_navigation")
        }
        subscribeUI(mainVM)
    }

    private fun subscribeUI(vm: MainVM) {
        vm.onEventCreateHabit.observe(this, Observer {
            CreateHabitFragment.newInstance().show(supportFragmentManager, "create_habit")
        })
    }

}
