package com.komandor.komandor.ui.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.navigation.NavController
import com.komandor.komandor.ui.activity.MainActivity
import timber.log.Timber

abstract class BaseFragmentDialog : AppCompatDialogFragment {
    protected abstract val layoutId: Int
    protected abstract val TAG: String


    lateinit var navController:NavController

    constructor() : super() {}

    lateinit var customView: View;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Simply return the already inflated custom view
        //dialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN or WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        return customView
    }

    abstract protected fun initUI()

    override fun onStart() {
        super.onStart()
        setWindowParams()
        requireActivity()?.let{
            if (it is MainActivity) {
                navController = (it as MainActivity).navController
            }
        }
    }

    private fun setWindowParams(){
        val w = dialog?.window
        w?.let{
            it.setBackgroundDrawableResource(android.R.color.transparent)
            it.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        customView = requireActivity().layoutInflater.inflate(layoutId, null)
        // Create Alert Dialog with your custom view
        return AlertDialog.Builder(requireContext())
            //.setTitle("Настройка проека")
            .setView(customView)
            //.setPositiveButton(android.R.string.ok, null)
            //.setNegativeButton(android.R.string.cancel, null)
            .create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        // Perform remaining operations here. No null issues.
        initUI()

    }


    fun showNextFragment(destination: Int, args: Bundle, label: String) {
        try {
            navController?.navigate(destination, args)
        } catch (e: IllegalArgumentException) {
            Timber.d("Can't open 2 links at once! label = " + label)
        } catch (e: IllegalStateException) {
            Timber.d("IllegalStateException not associated with a fragment manager label = " + label
            )
        }
    }

    fun showNextFragment(destination: Int, label: String) {
        val args = Bundle()
        showNextFragment(destination, args, label)
    }

}