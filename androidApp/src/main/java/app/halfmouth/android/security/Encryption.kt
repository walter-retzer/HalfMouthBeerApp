package app.halfmouth.android.security

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import kotlin.reflect.KClass

class Encryption(context: Context) : SecureStorage {
    private val masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val pref = EncryptedSharedPreferences.create(
        "halfmouth_encrypted_shared_preferences",
        masterKey,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    /** SecureStorage Functions */
    override fun put(key: String, value: Any) = with(pref.edit()) {
        when (value) {
            is String -> putString(key, value)
            is Float -> putFloat(key, value)
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is Boolean -> putBoolean(key, value)
            else -> throw IllegalArgumentException("Unsupported type: ${value::class.java}")
        }
        apply()
    }

    override fun get(key: String, clazz: KClass<*>): Any? {
        if (pref.contains(key).not()) return null
        return when (clazz.java) {
            String::class.java -> pref.getString(key, null)
            Int::class.java -> pref.getInt(key, -1)
            java.lang.Integer::class.java -> pref.getInt(key, -1)
            java.lang.Float::class.java -> pref.getFloat(key, -1f)
            java.lang.Long::class.java -> pref.getLong(key, -1L)
            java.lang.Boolean::class.java -> pref.getBoolean(key, false)
            else -> throw IllegalArgumentException(clazz.java.name)
        }
    }

    override fun delete(key: String) = pref.edit().remove(key).apply()

    override fun contains(key: String): Boolean = pref.contains(key)
}
