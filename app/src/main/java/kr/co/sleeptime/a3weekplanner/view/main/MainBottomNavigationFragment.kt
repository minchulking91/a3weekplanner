package kr.co.sleeptime.a3weekplanner.view.main

import android.view.MenuItem
import android.view.View
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_main_bottom_navigation.*
import kr.co.sleeptime.a3weekplanner.R
import kr.co.sleeptime.a3weekplanner.view.base.BaseBottomSheetFragment


class MainBottomNavigationFragment : BaseBottomSheetFragment(), NavigationView.OnNavigationItemSelectedListener {
    override val layoutRes: Int = R.layout.fragment_main_bottom_navigation

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_habits -> {

            }
            R.id.nav_history -> {

            }
            R.id.nav_today -> {

            }
        }
        dismiss()
        return true
    }

    override fun setupViews(view: View) {
        super.setupViews(view)
        nav_view.setNavigationItemSelectedListener(this)
    }

    companion object {
        fun newInstance(): MainBottomNavigationFragment {
            return MainBottomNavigationFragment()
        }
    }
}