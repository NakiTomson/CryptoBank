package com.example.remote.adapter

import android.util.Log
import com.google.gson.GsonBuilder
import com.courier.network.errors.ServerError
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Retrofit
import java.io.IOException

object ServerErrorConverter {


    fun <T : ServerError> ResponseBody?.convertTo(classType: Class<T>): ServerError {
        this ?: return ServerError.UndefinedError()
        return try {
            GsonBuilder().create().fromJson(string(), classType)
        } catch (e: Exception) {
            return ServerError.UndefinedError()
        }
    }


    fun <T> ResponseBody?.convertTo(retrofit: Retrofit?, classType: Class<T>): T? {
        if (this == null || retrofit == null) {
            return null
        }
        val converter = retrofit.responseBodyConverter<T>(classType, arrayOfNulls<Annotation>(0))
        return try {
            converter.convert(string().toResponseBody("application/json".toMediaTypeOrNull()))
        } catch (exception: IOException) {
            Log.v("Unable to convert", "${toString()} to ${classType.simpleName}")
            null
        }
    }
}