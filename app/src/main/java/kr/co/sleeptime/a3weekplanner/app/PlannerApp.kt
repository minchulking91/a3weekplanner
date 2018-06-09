package kr.co.sleeptime.a3weekplanner.app

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class PlannerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}