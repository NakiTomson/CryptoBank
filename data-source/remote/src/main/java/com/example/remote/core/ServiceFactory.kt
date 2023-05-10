package com.example.remote.core

interface ServiceFactory {
    fun <S> createService(serviceClass: Class<S>): S
}