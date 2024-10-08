package td.info507.bud.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import td.info507.bud.R
import td.info507.bud.activity.Updatable
import td.info507.bud.modele.CardMainModel
import td.info507.bud.storage.CardStorageMain

// Fragment de dialogue pour ajouter un élément
import android.util.Log

class DialogAdd(private val updatable: Updatable) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d("DialogAdd", "onCreateDialog called")

        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_add, null)

        val dialog = AlertDialog.Builder(context)
            .setView(view)
            .setPositiveButton("Valider", null) // On met le listener à null pour le gérer manuellement après
            .setNegativeButton("Annuler", null)
            .create()

        dialog.setOnShowListener {
            val button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            button.setOnClickListener {
                Log.d("DialogAdd", "Valider button clicked")

                val nomPlante = view.findViewById<EditText>(R.id.dialog_nom).text.toString()
                val typePlante = view.findViewById<EditText>(R.id.dialog_type).text.toString()

                Log.d("DialogAdd", "nomPlante: $nomPlante, typePlante: $typePlante")

                // Désactiver le bouton pour empêcher plusieurs clics
                button.isEnabled = false

                // Appel pour obtenir les détails de la plante
                getPlantDetails(typePlante) { plantDetails ->
                    Log.d("DialogAdd", "getPlantDetails callback triggered")

                    val ctx = context
                    if (plantDetails != null && ctx != null) {
                        Log.d("DialogAdd", "Plant details found: $plantDetails")

                        // Ajouter la plante avec les détails récupérés
                        CardStorageMain.get(ctx).insert(
                            CardMainModel(
                                0,
                                nomPlante,
                                typePlante,
                                plantDetails.getString("description"),
                                plantDetails.getString("taille"),
                                plantDetails.getString("arrosage"),
                                plantDetails.getString("lumiere"),
                                plantDetails.getString("difficulte"),
                                plantDetails.getString("image")
                            )
                        )
                        updatable.update()

                        // Fermer le dialogue après mise à jour
                        dialog.dismiss()
                    } else {
                        Log.d("DialogAdd", "Plant details not found or context is null")
                        Toast.makeText(ctx, "Erreur ou plante non trouvée", Toast.LENGTH_SHORT).show()

                        // Réactiver le bouton en cas d'erreur ou d'échec
                        button.isEnabled = true
                    }
                }
            }
        }

        return dialog
    }

    private fun getPlantDetails(type: String, callback: (JSONObject?) -> Unit) {
        Log.d("DialogAdd", "getPlantDetails called with type: $type")

        val ctx = context
        if (ctx == null) {
            Log.d("DialogAdd", "Context is null, exiting getPlantDetails")
            return
        }

        val queue = Volley.newRequestQueue(ctx)
        val request = JsonObjectRequest(
            Request.Method.GET,
            "https://mcida.fr/plants.json",
            null,
            { response ->
                Log.d("DialogAdd", "Response received from server")

                val plants = response.getJSONArray("plants")
                for (i in 0 until plants.length()) {
                    val plant = plants.getJSONObject(i)
                    if (plant.getString("nom") == type) {
                        Log.d("DialogAdd", "Matching plant found: $plant")
                        callback(plant)
                        return@JsonObjectRequest
                    }
                }
                Log.d("DialogAdd", "No matching plant found")
                callback(null)
            },
            { error ->
                Log.e("DialogAdd", "Error fetching data from server: ${error.message}")
                callback(null)
            }
        )
        queue.add(request)
    }
}