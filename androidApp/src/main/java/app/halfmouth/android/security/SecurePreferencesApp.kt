package app.halfmouth.android.security

import app.halfmouth.android.main.AndroidApp
import kotlin.reflect.KClass

class SecurePreferencesApp() {

    private val context = AndroidApp.applicationContext
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
