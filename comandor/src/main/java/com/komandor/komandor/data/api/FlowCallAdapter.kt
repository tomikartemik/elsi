package com.komandor.komandor.data.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import java.io.IOException
import java.lang.reflect.Type
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FlowCallAdapter<T>(
    private val retrofit: Retrofit,
    private val mapper: ExceptionMapper,
    private val responseType: Type
) : CallAdapter<T, Flow<T>> {
    override fun adapt(call: Call<T>): Flow<T> {
        return flow {
            emit(
                suspendCancellableCoroutine { continuation ->
                    call.enqueue(object : Callback<T> {

                        override fun onFailure(call: Call<T>, t: Throwable) {
                            Timber.d("onFailure t =  ${t}")
                            continuation.resumeWithException(t)
                        }

                        override fun onResponse(call: Call<T>, response: Response<T>) {
                           Timber.d("onResponse response isSuccessful = ${response.isSuccessful} ${response.code()}")
                            if (response.isSuccessful) {
                                continuation.resume(response.body()!!)
                            } else {
                                Timber.d("onResponse response raw = ${response.raw()} ")
                                //throw Exception("${response.code()} ${response.message()} ")
                                continuation.resumeWithException( Exception("${response.code()} ${response.message()} "))

                            }

                        }
                    })
                    continuation.invokeOnCancellation { call.cancel() }
                }
            )
        }
    }

    override fun responseType() = responseType

}
