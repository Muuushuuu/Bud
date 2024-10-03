package td.info507.bud.dialog

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import td.info507.bud.R

abstract class CardAdapter : RecyclerView.Adapter<CardAdapter.CardHolder>() {

    class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout: LinearLayout = itemView.findViewById(R.id.card_layout)
        /*val value: TextView = itemView.findViewById(R.id.card_value)
        val description: TextView = itemView.findViewById(R.id.card_description)*/
    }

    abstract fun onClickListener(view: View)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)
        view.setOnClickListener{
//            Toast.makeText(parent.context,"Coucou, ", Toast.LENGTH_LONG)
//            var intent = Intent(parent.context, CardActivity::class.java)
            onClickListener(view)
        }
        return CardHolder(view)
    }
    // Lie les données de l’item à son ViewHolder
    override fun onBindViewHolder(holder: CardHolder, position: Int){
        holder.layout.setBackgroundColor(Color.YELLOW)
        /*holder.value.text = "Kfé"
        holder.description.text = "Une pause s'impose"*/
    }
    // Retourne le nombre d’items total à afficher
    override fun getItemCount(): Int{
        return 10
    }
}