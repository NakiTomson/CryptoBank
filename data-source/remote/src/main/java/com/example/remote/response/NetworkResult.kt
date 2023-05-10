package com.example.remote.response

sealed class NetworkResult<out DT> {
    sealed class Response<out DT>(val code: Int) : NetworkResult<DT>() {
        class Success<out DT>(val data: DT?, code: Int) : Response<DT>(code) {

            val isSuccessful = code in 200..300
        }
        class Error(val error: ErrorResponse, code: Int) : Response<Nothing>(code)
    }

    class Exception(val exception: Throwable) : NetworkResult<Nothing>()
}
