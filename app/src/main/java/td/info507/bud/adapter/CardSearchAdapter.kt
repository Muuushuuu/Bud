package td.info507.bud.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import td.info507.bud.R
import td.info507.bud.modele.CardSearchModel
import td.info507.bud.storage.CardStorageSearch

abstract class CardSearchAdapter(private val context: Context, val cards: List<CardSearchModel>): RecyclerView.Adapter<CardSearchAdapter.CardHolder>() {

    class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val card: LinearLayout = itemView.findViewById(R.id.card_search_layout)
        val image: ImageView = itemView.findViewById(R.id.item_search_image)
        val type : TextView = itemView.findViewById(R.id.item_search_type)
        val description  : TextView = itemView.findViewById(R.id.item_search_description)
        val taille : TextView = itemView.findViewById(R.id.item_search_taille)
        val arrosage : TextView = itemView.findViewById(R.id.item_search_arrosage)
        val lumière : TextView = itemView.findViewById(R.id.item_search_lumiere)
        val difficulte : TextView = itemView.findViewById(R.id.item_search_difficulte)
    }

    abstract fun onClickListener(view: View)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        // Créer la vue de l'item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_card, parent, false)
        // Ajouter un Toast et un clic listener
        view.setOnClickListener {
//            Toast.makeText(parent.context, "Coucou Nathan", Toast.LENGTH_LONG).show()
//            onClickListener(view)
        }
        return CardHolder(view)
    }


    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val cards = CardStorageSearch.get(context).findAll()
        if (position < cards.size) {
            val card = cards[position]
            holder.type.text = card.nom
            holder.description.text = card.description
            holder.taille.text = card.taille
            holder.arrosage.text = card.arrosage
            holder.lumière.text = card.lumiere
            holder.difficulte.text = card.difficulte

            // Charger l'image Unsplash
            Glide.with(context)
                .load(card.image ?: "https://images.unsplash.com/photo-1497250681960-ef046c08a56e?q=80&w=2574&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")  // URL de l'image Unsplash
                .placeholder(R.drawable.flowertemp)  // Image de remplacement pendant le chargement
                .error("https://images.unsplash.com/photo-1497250681960-ef046c08a56e?q=80&w=2574&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")  // Image en cas d'erreur
                .into(holder.image)  // Mettre l'image dans l'ImageView
        } else {
            Log.e("CardAdapter", "Invalid position: $position")
        }
    }


    override fun getItemCount(): Int {
        return 15
    }
}