package com.komandor.komandor.data.api.model


sealed class BaseException(
    open val code: Int,
    override val message: String?
) : Throwable(message) {

    data class AlertException(
        override val code: Int,
        override val message: String,
        val title: String? = null
    ) : BaseException(code,  message)


}
