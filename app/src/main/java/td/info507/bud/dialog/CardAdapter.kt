package td.info507.bud.dialog

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import td.info507.bud.R

abstract class CardAdapter : RecyclerView.Adapter<CardAdapter.CardHolder>() {

    class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: LinearLayout = itemView.findViewById(R.id.card_layout)
        val image: ImageView = itemView.findViewById(R.id.item_image)
        val name : TextView = itemView.findViewById(R.id.item_name)
        val type : TextView = itemView.findViewById(R.id.item_type)
        val description  : TextView = itemView.findViewById(R.id.item_description)
        val taille : TextView = itemView.findViewById(R.id.item_taille)
        val arrosage : TextView = itemView.findViewById(R.id.item_arrosage)
        val lumière : TextView = itemView.findViewById(R.id.item_lumiere)
        val difficulte : TextView = itemView.findViewById(R.id.item_difficulte)
    }

    abstract fun onClickListener(view: View)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        // Ajouter le click listener
        view.setOnClickListener {
            // Afficher un Toast ==> Test
            Toast.makeText(parent.context, "Coucou Nathan", Toast.LENGTH_LONG).show()
            onClickListener(view)
        }
        return CardHolder(view)
    }

    // Lier les données de l’item à son ViewHolder
    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.name.text = "Plante Salon"
        holder.type.text = "Hibiscus"
        holder.description.text = "L'hibiscus est une plante tropicale aux fleurs éclatantes qui ajoutent une touche de couleur vibrante et exotique à tout espace."
        holder.taille.text = "60cm"
        holder.arrosage.text = "250ml / 7j"
        holder.lumière.text = "Lumière indirecte"
        holder.difficulte.text = "Moyen"
    }

    // Retourner le nombre d’items total à afficher
    override fun getItemCount(): Int {
        return 10
    }
}
