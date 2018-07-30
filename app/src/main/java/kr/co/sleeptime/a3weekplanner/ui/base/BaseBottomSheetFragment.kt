package kr.co.sleeptime.a3weekplanner.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.sleeptime.a3weekplanner.utils.KeyboardUtil


abstract class BaseBottomSheetFragment : BottomSheetDialogFragment() {

    @get:LayoutRes
    abstract val layoutRes: Int
    open val isUseDataBinding: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View?
        if (layoutRes != 0) {
            rootView = if (!isUseDataBinding) {
                inflater.inflate(layoutRes, container, false)
            } else {
                onDataBinding(inflater, container)
            }
            dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            activity?.let { activity ->
                rootView?.let { rootView ->
                    KeyboardUtil(activity, rootView)
                }
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