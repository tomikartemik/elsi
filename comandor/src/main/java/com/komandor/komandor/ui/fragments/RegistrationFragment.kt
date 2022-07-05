package com.komandor.komandor.ui.fragments

import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.komandor.komandor.R
import com.komandor.komandor.databinding.FrRegistrationBinding
import com.komandor.komandor.extension.disableCopyPaste
import com.komandor.komandor.extension.phoneNumberFormat
import com.komandor.komandor.ui.base.BaseFragment
import com.komandor.komandor.ui.base.viewBinding
import com.komandor.komandor.ui.viewmodel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : BaseFragment() {
    override val layoutId: Int = R.layout.fr_registration

    private val viewModel by viewModels<RegistrationViewModel>()
    private val binding by viewBinding<FrRegistrationBinding>()
    //private var phone:String =""
    private var code:String =""
    private var formattedNumber:String =""

    val liveData: MutableLiveData<String> = MutableLiveData()



    override fun initUI() {
        initStateWatcher(viewModel.state)

        liveData.observe(viewLifecycleOwner,  {
            formattedNumber = it
        })
        binding.etPhone.apply {
            disableCopyPaste()
            imeOptions = EditorInfo.IME_ACTION_DONE
            phoneNumberFormat(liveData)

            setOnFocusChangeListener { view, hasFocus ->
                error = null
                isCursorVisible = hasFocus
                Unit
            }

            setOnEditorActionListener { v, actionId, event ->
                //L.d(TAG, "formattedNumber = "+formattedNumber+" length = "+formattedNumber.length+" checkPhone = "+formattedNumber.checkPhone())
                error = if (viewModel.isValidPhone(formattedNumber)) {
                    null
                } else {
                    "Некорректный номер телефона."
                }
                false
            }

            doOnTextChanged { text, start, count, after ->
                //L.d(TAG, "text = "+text+" length = "+text.toString().trim().checkPhone())
                if (viewModel.isValidPhone(formattedNumber)) {
                 //   onEditorAction(EditorInfo.IME_ACTION_DONE)
                }
            }
        }

        binding.etCode.apply {
            disableCopyPaste()
            imeOptions = EditorInfo.IME_ACTION_DONE

            setOnFocusChangeListener { view, hasFocus ->
                error = null
                isCursorVisible = hasFocus
                Unit
            }

            setOnEditorActionListener { v, actionId, event ->
                //L.d(TAG, "formattedNumber = "+formattedNumber+" length = "+formattedNumber.length+" checkPhone = "+formattedNumber.checkPhone())
                error = if (viewModel.isValidCode(code)) {
                    null
                } else {
                    "Некорректный код подтверждения."
                }
                false
            }

            doOnTextChanged { text, start, count, after ->
                //Timber.d( "code text = "+text)
                code = text.toString()
                if (viewModel.isValidCode(code)) {
                   // onEditorAction(EditorInfo.IME_ACTION_DONE)
                }
            }
        }

        binding.tilAuthCode.visibility = View.GONE
        binding.btnAuthCheckCode.visibility = View.GONE

        binding.btnAuthGetCode.visibility = View.VISIBLE

        binding.btnAuthGetCode.setOnClickListener {
            binding.btnAuthGetCode.visibility = View.GONE

            binding.btnAuthCheckCode.visibility = View.VISIBLE
            binding.tilAuthCode.visibility = View.VISIBLE

            if (viewModel.isValidPhone(formattedNumber) ) {
                viewModel.registryPhone(formattedNumber)
            }
        }
        binding.btnAuthCheckCode.setOnClickListener {
            if ( viewModel.isValidCode(code)) {
               // viewModel.confirmPhone(code)
            }
        }
    }

    override fun onComplete(data: Any?) {
        toScreen(R.id.chatsFragment)
    }

    override fun back() {
        requireActivity().finish()
    }
}