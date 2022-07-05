package com.komandor.komandor.ui.fragments

import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.komandor.komandor.R
import com.komandor.komandor.data.database.model.LocalCertificate
import com.komandor.komandor.databinding.FrAuthBinding
import com.komandor.komandor.extension.disableCopyPaste
import com.komandor.komandor.ui.base.BaseFragment
import com.komandor.komandor.ui.base.viewBinding
import com.komandor.komandor.utils.SoftKb
import com.komandor.komandor.ui.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : BaseFragment() {
    override val layoutId: Int = R.layout.fr_auth

    private val viewModel by viewModels<AuthViewModel>()
    private val binding by viewBinding<FrAuthBinding>()

    private var password: String = ""

    private val args: AuthFragmentArgs by navArgs()

    override fun initUI() {
        binding.etPassword.apply {
            disableCopyPaste()
            imeOptions = EditorInfo.IME_ACTION_DONE

            setText(password)
            doOnTextChanged { text, start, count, after ->
                password = text.toString()
            }

            setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    error = null
                } else {
                    isCursorVisible = false
                }
                Unit
            }
            setOnEditorActionListener { v, actionId, event ->
                password = v.text.toString()
                if (password.length < 1) {
                    error = "введите пароль"
                } else {
                    v.clearFocus()
                    isCursorVisible = false
                    binding.btnCertValidationLogin.isEnabled = false

                    viewModel.validatePassword(password = password)

                }
                false
            }
        }

        binding.btnCertValidationLogin.setOnClickListener {
            binding.btnCertValidationLogin.isEnabled = false
            SoftKb.hideSoftKeyboard(requireContext(), it)
            viewModel.validatePassword(password = password)
        }
        binding.cvPassowrd.visibility = View.GONE
        initStateWatcher(viewModel.state)
        viewModel.getCertificate(args.key)

    }

    override fun afterError(exception: Throwable) {
        toScreen(R.id.startInfoFragment)
    }

    override fun onComplete(data: Any?) {
        if (data != null) {

            if (data is Boolean) {
                if (data) {
                    toScreen(R.id.registrationFragment)
                } else {
                    toScreen(R.id.chatsFragment)
                }
            } else if (data is LocalCertificate){
                binding.cvPassowrd.visibility = View.VISIBLE
            }
        }
    }
}