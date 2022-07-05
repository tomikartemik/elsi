package com.komandor.komandor.ui.fragments

import androidx.fragment.app.viewModels
import com.komandor.komandor.R
import com.komandor.komandor.databinding.FrContactsBinding
import com.komandor.komandor.ui.base.BaseFragment
import com.komandor.komandor.ui.base.viewBinding
import com.komandor.komandor.ui.viewmodel.ContactsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsFragment : BaseFragment() {
    override val layoutId: Int = R.layout.fr_contacts
    private val viewModel by viewModels<ContactsViewModel>()
    private val binding by viewBinding<FrContactsBinding>()

    override fun initUI() {
        initStateWatcher(viewModel.state)

    }
    override fun onComplete(data: Any?) {
        TODO("Not yet implemented")
    }

}