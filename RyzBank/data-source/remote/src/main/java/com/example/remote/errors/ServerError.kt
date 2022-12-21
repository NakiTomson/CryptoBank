package com.courier.network.errors

open class ServerError : Throwable() {
    class UndefinedError : ServerError()
    class NotError : ServerError()
}