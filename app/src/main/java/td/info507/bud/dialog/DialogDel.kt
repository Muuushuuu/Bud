package td.info507.bud.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import td.info507.bud.R
import td.info507.bud.activity.Updatable
import td.info507.bud.storage.CardStorageMain

class DialogDel(private val itemId: Int, private val context : Context, private val updatable: Updatable) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_del, null)

        return AlertDialog.Builder(requireContext())  // Use requireContext()
            .setView(view)
            .setPositiveButton("Valider") { _, _ ->  // Provide a lambda listener
                supprimer(itemId, updatable)  // Call supprimer on positive button click
            }
            .setNegativeButton("Annuler", null)  // No action on negative button
            .create()  // Return the created dialog
    }

    private fun supprimer(itemId: Int, updatable: Updatable) {
        CardStorageMain.get(context).delete(itemId)
        Log.d("deletttt", "delete de : " + itemId.toString())
        updatable.update()
    }
}
