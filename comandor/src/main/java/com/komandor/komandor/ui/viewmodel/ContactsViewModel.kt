package com.komandor.komandor.ui.viewmodel

import com.atom.ui.viewmodel.BaseViewState
import com.komandor.komandor.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor() : BaseViewModel(BaseViewState(isLoading = false)) {

}