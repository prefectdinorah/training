package core.datastore

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import core.coroutine.withContextIO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EncryptedTokenDataStore @Inject constructor(
    private val context: Context,
) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs by lazy {
        EncryptedSharedPreferences.create(
            context,
            "auth_tokens",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    }

    suspend fun getAccessToken(): String? = withContextIO {
        prefs.getString(KEY_ACCESS, null)
    }

    suspend fun getRefreshToken(): String? = withContextIO {
        prefs.getString(KEY_REFRESH, null)
    }

    suspend fun saveTokens(access: String, refresh: String) = withContextIO {
        prefs.edit {
            putString(KEY_ACCESS, access)
            putString(KEY_REFRESH, refresh)
        }
    }

    suspend fun clear() = withContextIO {
        prefs.edit { clear() }
    }

    companion object {
        private const val KEY_ACCESS = "access_token"
        private const val KEY_REFRESH = "refresh_token"
    }
}