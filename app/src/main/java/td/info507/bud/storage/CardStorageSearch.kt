package td.info507.bud.storage

import android.content.Context
import android.content.SharedPreferences
import td.info507.bud.modele.CardSearchModel
import td.info507.bud.storage.utility.Storage

object CardStorageSearch {
    private const val LOGIN = "login"
    private const val STORAGE = "storage"
    const val NONE = 0
    const val FILE_JSON = 1

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("td.info507.bud.preferences", Context.MODE_PRIVATE)
    }

    fun getLogin(context: Context): String? {
        return getPreferences(context).getString(LOGIN, "")
    }

    fun setLogin(context: Context, prefLogin: String) {
        getPreferences(context).edit().putString(LOGIN, prefLogin).apply()
    }

    fun getStorage(context: Context): Int {
        val prefStorage = getPreferences(context).getInt(STORAGE, NONE)
        return if (prefStorage == NONE) {
            FILE_JSON // Si aucune préférence, on choisit FILE_JSON par défaut
        } else {
            prefStorage
        }
    }

    fun setStorage(context: Context, prefStorage: Int) {
        getPreferences(context).edit().putInt(STORAGE, prefStorage).apply()
    }

    fun get(context: Context): Storage<CardSearchModel> {
        var storage: Storage<CardSearchModel> = CardNoneStorageSearch()
        when (getStorage(context)) {
            NONE -> storage = CardNoneStorageSearch()
            FILE_JSON -> storage = CardJSONFileStorageSearch(context)
        }
        return storage
    }
}