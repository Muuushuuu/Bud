package td.info507.bud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import td.info507.bud.R

class CardEvolutionAdapter: RecyclerView.Adapter<CardEvolutionAdapter.CardHolder>() {

    // ViewHolder pour chaque élément de la RecyclerView
    class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.item_evolution_image)
        val date: TextView = itemView.findViewById(R.id.item_evolution_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        // Créer la vue de l'item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_evolution_card, parent, false)
        return CardHolder(view)
    }

    // Liaison des données à l'item
    // A remplacer par les informations dans le json
    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(R.drawable.hibiscus) // Image par défaut pour l'exemple
            .into(holder.img)
        holder.date.text = "29/06/03"
    }

    // Nombre d'items à afficher
    override fun getItemCount(): Int {
        return 10
    }
}