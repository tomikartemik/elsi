package com.komandor.komandor.data.api

import android.content.Context
import com.komandor.komandor.R
import com.komandor.komandor.data.api.model.BaseException


import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

class ExceptionMapper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun mapperToBaseException(throwable: RetrofitException): Throwable {
        Timber.d("mapperToBaseException throwable = $throwable")
        Timber.d("mapperToBaseException getKind = ${throwable.getKind()}")
        Timber.d("mapperToBaseException getUrl = ${throwable.getUrl()}")
        Timber.d("mapperToBaseException getResponse = ${throwable.getResponse()}")

        Timber.d("mapperToBaseException message = ${throwable.message}")
        Timber.d("mapperToBaseException ex = ${throwable.getExeption()}")

        return when (throwable.getKind()) {
            RetrofitException.Kind.NETWORK ->
                BaseException.AlertException(
                    code = if (throwable.message != null) 500 else -1,
                    message = throwable.message
                        ?: context.getString(R.string.internet_connection_error)
                )
            else -> {
                throwable
            }
        }
    }
}
