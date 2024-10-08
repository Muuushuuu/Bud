package td.info507.bud.request

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import td.info507.bud.activity.Updatable
import td.info507.bud.modele.Card
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
//                Log.d("Requesttttt", res.toString())
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
                Card(
                    0,
                    card.optString(Card.NOM, "Nom inconnu"),
                    card.optString(Card.DESCRIPTION, "Description non disponible"),
                    card.optString(Card.TAILLE, "Taille non spécifiée"),
                    card.optString(Card.ARROSAGE, "Arrosage inconnu"),
                    card.optString(Card.LUMIERE, "Lumière non précisée"),
                    card.optString(Card.DIFFICULTE, "Difficulté non précisée"),
                )
            )
        }
    }
}