package td.info507.bud.request

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import td.info507.bud.activity.Updatable
import td.info507.bud.modele.CardSearchModel
import td.info507.bud.storage.CardStorage

class CardRequest(
    private val context: Context,
    updatable: Updatable
) {
    init {
        val queue = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(
            Request.Method.GET,
            "https://mcida.fr/plants.json",
            null,
            { res ->
                delete()
                insert(res.getJSONArray("plants"))
                updatable.update()
                Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
            },
            { err -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show() }
        )
        queue.add(request)
        queue.start()
    }

    private fun delete() {
        for (card in CardStorage.get(context).findAll()) {
            CardStorage.get(context).delete(card.id)
        }
    }

    private fun insert(array: JSONArray) {
        for (i in 0 until array.length()) {
            val card = array.getJSONObject(i)
            CardStorage.get(context).insert(
                CardSearchModel(
                    0,
                    card.optString(CardSearchModel.NOM, "Nom inconnu"),
                    card.optString(CardSearchModel.DESCRIPTION, "Description non disponible"),
                    card.optString(CardSearchModel.TAILLE, "Taille non spécifiée"),
                    card.optString(CardSearchModel.ARROSAGE, "Arrosage inconnu"),
                    card.optString(CardSearchModel.LUMIERE, "Lumière non précisée"),
                    card.optString(CardSearchModel.DIFFICULTE, "Difficulté non précisée"),
                    card.optString(CardSearchModel.IMAGE, "https://images.unsplash.com/photo-1497250681960-ef046c08a56e?q=80&w=2574&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
                )
            )
        }
    }
}