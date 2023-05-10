package com.example.remote.adapter

import com.example.remote.response.NetworkResult
import okhttp3.Request
import okio.Timeout
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultCallAdapter(
    private val responseType: Type,
    private val responseBodyToErrorResponseConverter: ResponseBodyToErrorResponseConverter,
    private val retrofit: Retrofit
) : CallAdapter<Type, Call<NetworkResult<Type>>> {

    override fun adapt(call: Call<Type>): Call<NetworkResult<Type>> =
        ResultCall(call, responseBodyToErrorResponseConverter, retrofit)

    override fun responseType(): Type = responseType

    abstract class CallDelegate<TIn, TOut>(protected val proxy: Call<TIn>) : Call<TOut> {

        override fun execute(): Response<TOut> = throw NotImplementedError()
        final override fun enqueue(callback: Callback<TOut>) = enqueueImpl(callback)
        final override fun clone(): Call<TOut> = cloneImpl()

        override fun cancel() = proxy.cancel()
        override fun request(): Request = proxy.request()
        override fun isExecuted() = proxy.isExecuted
        override fun isCanceled() = proxy.isCanceled
        override fun timeout(): Timeout  = proxy.timeout()

        abstract fun enqueueImpl(callback: Callback<TOut>)
        abstract fun cloneImpl(): Call<TOut>
    }

    class ResultCall<T>(
        proxy: Call<T>,
        private val responseBodyToErrorResponseConverter: ResponseBodyToErrorResponseConverter,
        private val retrofit: Retrofit
    ) : CallDelegate<T, NetworkResult<T>>(proxy) {
        override fun enqueueImpl(callback: Callback<NetworkResult<T>>) =
            proxy.enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val code = response.code()
                    val result = if (response.isSuccessful) {
                        val body: T? = response.body()
                        NetworkResult.Response.Success(body, code)
                    } else {
                        NetworkResult.Response.Error(
                            responseBodyToErrorResponseConverter.convert(response, retrofit),
                            code
                        )
                    }
                    callback.onResponse(this@ResultCall, Response.success(result))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    callback.onResponse(
                        this@ResultCall,
                        Response.success(NetworkResult.Exception(t))
                    )
                }
            })

        override fun cloneImpl() = ResultCall(proxy.clone(), responseBodyToErrorResponseConverter, retrofit)
    }

    class Factory(
        private val responseBodyToErrorResponseConverter: ResponseBodyToErrorResponseConverter
    ) : CallAdapter.Factory() {
        override fun get(
            returnType: Type,
            annotations: Array<Annotation>,
            retrofit: Retrofit
        ): CallAdapter<*, *>? = when (getRawType(returnType)) {
            Call::class.java -> {
                val callType = getParameterUpperBound(0, returnType as ParameterizedType)
                when (getRawType(callType)) {
                    NetworkResult::class.java -> {
                        val resultType = getParameterUpperBound(0, callType as ParameterizedType)
                        ResultCallAdapter(resultType, responseBodyToErrorResponseConverter, retrofit)
                    }
                    else -> null
                }
            }
            else -> null
        }
    }
}
