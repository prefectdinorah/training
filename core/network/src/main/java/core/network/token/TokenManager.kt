package core.network.token

import core.datastore.EncryptedTokenDataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor(
    private val dataStore: EncryptedTokenDataStore,
) {
    @Volatile
    private var accessToken: String? = null

    suspend fun getAccessToken(): String? =
        accessToken ?: dataStore.getAccessToken().also { accessToken = it }

    suspend fun getRefreshToken(): String? = dataStore.getRefreshToken()

    suspend fun saveTokens(access: String, refresh: String) {
        accessToken = access
        dataStore.saveTokens(access, refresh)
    }

    suspend fun clear() {
        accessToken = null
        dataStore.clear()
    }

    fun getAccessTokenSync(): String? = accessToken
}