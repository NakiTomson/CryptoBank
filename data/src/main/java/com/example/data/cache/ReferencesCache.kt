package com.example.data.cache

import com.example.core.launchIO
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.*

enum class Reference

/* Кэш для хранения каких-либо данных в памяти с привязкой к [Reference]. */
@Suppress("UNCHECKED_CAST")
class ReferencesCache {
    private val hashMap = mutableMapOf<Reference, MutableMap<String, Any?>>()
    private val changesFlow = MutableSharedFlow<Pair<Reference, String>>()

    @OptIn(DelicateCoroutinesApi::class)
    fun <T : Any> setValue(key: String, reference: Reference, value: T?) {
        var referenceMap = hashMap[reference]
        if (referenceMap == null) {
            referenceMap = mutableMapOf()
            hashMap[reference] = referenceMap
        }
        referenceMap[key] = value
        launchIO { changesFlow.emit(reference to key) }
    }

    fun <T : Any> getValue(key: String, reference: Reference, default: T? = null): T? =
        (hashMap[reference]?.get(key) ?: default) as? T?

    fun <T : Any> getValueFlow(key: String, reference: Reference): Flow<T?> =
        changesFlow.filter { (t1, t2) -> t1 == reference && t2 == key }
            .map { (reference, key) -> getValue<T>(key, reference) }
            .distinctUntilChanged()
            .onStart { emit(getValue(key, reference)) }

    fun <T : Any> requireValue(key: String, reference: Reference): T = (hashMap[reference]?.get(key)
        ?: error("Value with key {$key} and reference {${reference.name}} not found")) as T

    fun getKey(value: Any, reference: Reference): String? =
        hashMap[reference]?.filter { it.value == value }?.keys?.firstOrNull()

    fun clear(reference: Reference) = hashMap[reference]?.clear()

    fun clear() = hashMap.clear()
}
