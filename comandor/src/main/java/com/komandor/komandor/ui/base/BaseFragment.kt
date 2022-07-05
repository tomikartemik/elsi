package com.komandor.komandor.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.atom.ui.viewmodel.BaseViewState
import com.komandor.komandor.ui.activity.MainActivity
import com.komandor.komandor.ui.dialog.ConfirmDialog
import com.komandor.komandor.ui.dialog.WarningDialog
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

import timber.log.Timber

abstract class BaseFragment : Fragment() , IFragmentNav {

    protected abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutId, container, false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerOnBackPressedCallback(activity as AppCompatActivity?, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    fun initStateWatcher(state: StateFlow<BaseViewState>) {
        state.onEach {
            //Timber.d("state= $it")
            if (it.isLoading) {
                showProgress()
            } else {
                hideProgress()
                if (it.exception != null) {
                    Timber.d("err = ${it.exception}")
                    showWarning(it.exception.message?:"Unknoun error", {
                        afterError(it.exception)
                    })
                } else if (it.isComplete) {
                    onComplete(it.payload)
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    open protected fun afterError(exception: Throwable){}
    abstract protected fun onComplete(data:Any?)

    abstract protected fun initUI()

    fun showProgress() {
        requireActivity()?.let{
            if (it is MainActivity) {
                it.showProgress()
            }
        }
    }

    fun hideProgress() {
        requireActivity()?.let{
            if (it is MainActivity) {
                it.hideProgress()
            }
        }
    }

    protected fun showConfirm(msg: String, callerOk: () -> Unit = {}, callerCancel: () -> Unit = {}) {
        ConfirmDialog(msg, callerOk, callerCancel).show(parentFragmentManager, "confirm")
    }

    protected fun showWarning(msg: String, callerOk: () -> Unit = {}) {
        WarningDialog(msg, callerOk).show(parentFragmentManager, "confirm")
    }

    override fun handleBackPressed(activity: AppCompatActivity, fragmentTwo: Fragment) {
        back()
    }

    open fun toScreen(destination: Int, args: Bundle?) {
        try {
            findNavController().navigate(destination, args?:Bundle())

        } catch (e: IllegalArgumentException) {
            Timber.d( "e = ${e}")
        } catch (e: IllegalStateException) {
            Timber.d( "IllegalStateException not associated with a fragment manager ")
        }
    }

    open fun toScreen(destination: Int) {
        try {
            findNavController().navigate(destination)

        } catch (e: IllegalArgumentException) {
            Timber.d( "e = ${e}")
        } catch (e: IllegalStateException) {
            Timber.d( "IllegalStateException not associated with a fragment manager ")
        }
    }

    fun backStack(@IdRes destinationId:Int) {
        findNavController().popBackStack(destinationId,false)
    }

    open fun back() {
        Timber.d( "back")

        findNavController().popBackStack()
    }

}