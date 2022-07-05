package com.atom.ui.viewmodel



data class BaseViewState(
    override val isLoading: Boolean = false,
    override val exception: Throwable? = null,
    override val isComplete: Boolean = false,
    val payload:Any?=null

    ) : ViewState(isLoading,isComplete,  exception ) {

    }
