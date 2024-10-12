package td.info507.bud.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import td.info507.bud.R
import td.info507.bud.modele.CardEvolutionModel
import td.info507.bud.storage.CardStorageEvolution

abstract class CardEvolutionAdapter(
    private val context: Context,
    private val plantId: Int,  // Ajout du plantId ici
    private val evolutions: List<CardEvolutionModel>
) : RecyclerView.Adapter<CardEvolutionAdapter.CardHolder>() {

    // ViewHolder pour chaque élément de la RecyclerView
    class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.item_evolution_image)
        val date: TextView = itemView.findViewById(R.id.item_evolution_date)
    }

    // Méthode abstraite pour définir une action lors du clic
    abstract fun onClickListener(view: View)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        // Créer la vue de l'item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_evolution_card, parent, false)
        // Ajouter un listener de clic
        view.setOnClickListener {
            onClickListener(view)
        }
        return CardHolder(view)
    }

    // Liaison des données à l'item
    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val evolutions = CardStorageEvolution.get(context, plantId).findAll()  // Utilisation du plantId
        if (position < evolutions.size) {
            val evolution = evolutions[position]

            // Charger l'image avec Glide
            Glide.with(context)
                .load(evolution.img_src)
                .placeholder(R.drawable.flowertemp)
                .error(R.drawable.flowertemp)  // Image en cas d'erreur
                .into(holder.img)

            // Afficher la date
            holder.date.text = evolution.date

            // Mettre l'ID en tag pour une éventuelle utilisation
            holder.itemView.tag = evolution.id
        }
    }

    // Retourner la taille de la liste d'évolution
    override fun getItemCount(): Int {
        return CardStorageEvolution.get(context, plantId).size()  // Utilisation du plantId ici aussi
    }
}