package td.info507.bud.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import td.info507.bud.R

// Fragment de dialogue pour ajouter un élément
class dialogAdd : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Charger la vue du layout pour le dialogue
        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_add, null)

        // Créer le dialogue avec les boutons Ajouter et Annuler
        return AlertDialog.Builder(context)
            .setView(view)  // Associer la vue personnalisée
            .setPositiveButton("Ajouter", null)  // Bouton Ajouter /!\ a relier avec la fonction ajouter
            .setNegativeButton("Annuler", null)  // Bouton Annuler
            .create()
    }
}