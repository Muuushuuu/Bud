package td.info507.bud.dialog

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import td.info507.bud.R

abstract class CardAdapter : RecyclerView.Adapter<CardAdapter.CardHolder>() {

    class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout: LinearLayout = itemView.findViewById(R.id.card_layout)
    }

    abstract fun onClickListener(view: View)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)

        // Ajouter le click listener
        view.setOnClickListener {
            // Afficher un Toast
            Toast.makeText(parent.context, "Coucou Nathan", Toast.LENGTH_LONG).show()

            // Appel de la méthode abstraite onClickListener
            onClickListener(view)
        }

        return CardHolder(view)
    }

    // Lier les données de l’item à son ViewHolder
    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        // Ajouter ici la logique pour remplir les données de la vue si nécessaire
    }

    // Retourner le nombre d’items total à afficher
    override fun getItemCount(): Int {
        return 10
    }
}
