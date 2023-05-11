package com.example.remote.errors

open class ServerError : Throwable() {
    class UndefinedError : ServerError()
    class NotFoundError : ServerError()
    class NetworkError : ServerError()
}