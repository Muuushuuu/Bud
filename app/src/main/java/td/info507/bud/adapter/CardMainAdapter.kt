package td.info507.bud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import td.info507.bud.R

// Adapter personnalisé pour RecyclerView
abstract class CardMainAdapter : RecyclerView.Adapter<CardMainAdapter.CardHolder>() {

    // ViewHolder pour chaque élément de la RecyclerView
    class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Récupération des vues de l'item
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

    // Méthode abstraite pour définir une action lors du clic
    abstract fun onClickListener(view: View)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        // Créer la vue de l'item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        // Ajouter un Toast et un clic listener
        view.setOnClickListener {
            Toast.makeText(parent.context, "Coucou Nathan", Toast.LENGTH_LONG).show()
            onClickListener(view)
        }
        return CardHolder(view)
    }

    // Liaison des données à l'item
    // A remplacer par les informations dans le json
    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.name.text = "Plante Salon"
        holder.type.text = "Hibiscus"
        holder.description.text = "L'hibiscus est une plante tropicale aux fleurs éclatantes qui ajoutent une touche de couleur vibrante et exotique à tout espace."
        holder.taille.text = "60cm"
        holder.arrosage.text = "250ml / 7j"
        holder.lumière.text = "Lumière indirecte"
        holder.difficulte.text = "Moyen"
    }

    // Nombre d'items à afficher
    override fun getItemCount(): Int {
        return 10
    }
}