package com.atom.ui.viewmodel


open class ViewState(
    open val isLoading: Boolean = false,
    open val isComplete: Boolean = false,
    open val exception: Throwable? = null,
)



