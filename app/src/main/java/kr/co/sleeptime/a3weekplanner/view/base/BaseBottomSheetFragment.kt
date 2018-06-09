package kr.co.sleeptime.a3weekplanner.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


abstract class BaseBottomSheetFragment : BottomSheetDialogFragment() {

    @get:LayoutRes
    abstract val layoutRes: Int
    open val isUseDataBinding: Boolean = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View?
        if (layoutRes != 0) {
            rootView = if (!isUseDataBinding) {
                inflater.inflate(layoutRes, container, false)
            } else {
                onDataBinding(inflater, container)
            }

            return rootView
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    open fun onDataBinding(inflater: LayoutInflater, container: ViewGroup?): View? {
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
    }

    open fun setupViews(view: View) {

    }
}