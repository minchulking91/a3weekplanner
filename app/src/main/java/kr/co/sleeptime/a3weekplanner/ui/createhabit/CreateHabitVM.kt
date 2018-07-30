package kr.co.sleeptime.a3weekplanner.ui.createhabit

import android.app.Application
import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.ImageButton
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kr.co.sleeptime.a3weekplanner.R
import kr.co.sleeptime.a3weekplanner.app.PlannerApp

class CreateHabitVM(application: Application) : AndroidViewModel(application) {

    val expandButton: MutableLiveData<Boolean> = MutableLiveData()
    val descriptionVisible: MutableLiveData<Boolean> = MutableLiveData()
    fun onClickSave() {

    }

    fun onClickExpand(view: View) {
        expandButton.value = true
        (view as? ImageButton)?.let {
            (it.drawable as? AnimationDrawable)?.start()
            it.setImageDrawable(getApplication<PlannerApp>().getDrawable(R.drawable.add_cancel_expand_anim))
        }
    }

    fun onClickCollapse(view: View) {
        expandButton.value = false
        (view as? ImageButton)?.let {
            (it.drawable as? AnimationDrawable)?.start()
            it.setImageDrawable(getApplication<PlannerApp>().getDrawable(R.drawable.add_cancel_collapse_anim))
        }
    }

    fun onClickAddDescription(){
        descriptionVisible.value = true
    }
    fun onClickAddAlarm(){

    }
}