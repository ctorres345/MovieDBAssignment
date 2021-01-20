package com.backbase.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.backbase.data.BuildConfig
import com.backbase.data.util.Preferences
import com.backbase.data.util.SHARED_PREFERENCES_APP
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(@ApplicationContext private val context: Context) {

    private val sp : SharedPreferences by lazy {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        EncryptedSharedPreferences.create(
            SHARED_PREFERENCES_APP,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    /**
     * TODO This could use base64 from commons.codec.binary, raise api level for that and change
     */
    fun getDefaultApiToken() : String {
        val encryptedToken = BuildConfig.MOVIE_API_TOKEN
        val data = Base64.decode(encryptedToken, Base64.DEFAULT)
        return String(data)
    }

    fun saveToken(token: String) {
        sp.edit().apply {
            putString(Preferences.API_TOKEN, token)
            apply()
        }
    }

    fun getToken() : String? {
        return sp.getString(Preferences.API_TOKEN, getDefaultApiToken())
    }

    fun clearData() {
        sp.edit().clear().apply()
    }
}