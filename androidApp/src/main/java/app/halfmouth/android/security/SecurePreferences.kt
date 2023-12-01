package app.halfmouth.android.security

import android.content.Context
import kotlin.reflect.KClass

class SecurePreferences(context: Context) {

    private val storage: SecureStorage =  Encryption(context)

    @SuppressWarnings("SwallowedException", "CastToNullableType")
    internal inline fun <reified T : Any> get(key: String): T? = try {
        storage.get(key, T::class) as T?
    } catch (e: Exception) { null }

    fun put(key: String, value: Any) = storage.put(key, value)
    fun delete(key: String) = storage.delete(key)
    fun contains(key: String) = storage.contains(key)
}

interface SecureStorage {
    fun put(key: String, value: Any)
    fun get(key: String, clazz: KClass<*>): Any?
    fun delete(key: String)
    fun contains(key: String): Boolean
}
