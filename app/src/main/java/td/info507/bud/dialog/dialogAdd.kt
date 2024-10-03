package td.info507.bud.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import td.info507.bud.R

class dialogAdd : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_add, null)
        return AlertDialog.Builder(context)
            .setView(view)
            .setPositiveButton("Ajouter", null)
            .setNegativeButton("Annuler", null)
            .create()

    }
}