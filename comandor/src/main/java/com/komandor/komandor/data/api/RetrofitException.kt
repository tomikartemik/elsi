package com.komandor.komandor.data.api

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import java.io.IOException

class RetrofitException(
    private val _message: String?,
    private val _url: String?,
    private val _response: Response<*>?,
    private val _kind: Kind,
    private val _exception: Throwable?,
    private val _retrofit: Retrofit?
) : RuntimeException(_message, _exception) {


    companion object {
        fun httpError(url: String, response: Response<*>?, retrofit: Retrofit): RetrofitException {

            val message = response?.code().toString() + " " + response?.message()
            Timber.d("httpError message = $message")
            return RetrofitException(message, url, response, Kind.HTTP, null, retrofit)
        }

        fun httpErrorWithObject(
            url: String,
            response: Response<*>?,
            retrofit: Retrofit
        ): RetrofitException {

            val message = response?.code().toString() + " " + response?.message()+ " "
            return RetrofitException(message, url, response, Kind.HTTP, null, retrofit)
        }

        fun networkError(exception: IOException): RetrofitException {
            Timber.d("networkError exception = $exception")
            return RetrofitException(exception.message, null, null, Kind.NETWORK, exception, null)
        }

        fun unexpectedError(exception: Throwable): RetrofitException {
            Timber.d("unexpectedError exception = ${exception.message}")
            return RetrofitException(
                exception.message,
                null,
                null,
                Kind.UNEXPECTED,
                exception,
                null
            )
        }
    }

    /** The request URL which produced the error. */
    fun getUrl() = _url

    /** Response object containing status code, headers, body, etc. */
    fun getResponse() = _response

    /** The event kind which triggered this error. */
    fun getKind() = _kind

    /** The Retrofit this request was executed on */
    fun getRetrofit() = _retrofit

    /** The data returned from the server in the response body*/


    fun getExeption(): Throwable? = _exception

    private fun deserializeServerError() {
        val responseBody = _response?.errorBody()
        Timber.d("httpErrorWithObject  errorBody = ${responseBody} }")
        if (responseBody != null) {
            Timber.d("httpErrorWithObject  body =  ${_response?.body().toString()}")
        }
    }

    /**
     * HTTP response body converted to specified `type`. `null` if there is no
     * response.
     * @throws IOException if unable to convert the body to the specified `type`.
     */
    @Throws(IOException::class)
    fun <T> ResponseBody.getErrorBodyAs(type: Class<T>): T? {
        Timber.d("getErrorBodyAs  _retrofit = ${_retrofit} this = ${this} }")

        if (_retrofit == null) {
            return null
        }

        val converter: Converter<ResponseBody, T> =
            _retrofit.responseBodyConverter(type, arrayOfNulls<Annotation>(0))
        Timber.d("getErrorBodyAs  converter = ${converter} }")

        return converter.convert(this)
    }

    enum class Kind {
        /** An [IOException] occurred while communicating to the server.  */
        NETWORK,
        /** A non-200 HTTP status code was received from the server.  */
        HTTP,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }
}
