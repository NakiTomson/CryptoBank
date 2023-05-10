package com.example.remote.errors

open class ServerError : Throwable() {
    class UndefinedError : ServerError()
    class NotError : ServerError()
}