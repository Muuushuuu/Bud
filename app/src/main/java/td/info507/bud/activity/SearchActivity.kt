package td.info507.bud.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import td.info507.bud.R
import td.info507.bud.adapter.CardAdapter

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        val list_of_plant = findViewById<RecyclerView>(R.id.card_list_of_plants)

        // Implémentation de l'adapter avec une définition de onClickListener
        list_of_plant.adapter = object : CardAdapter() {
            override fun onClickListener(view: View) {
                println("Item clicked")
            }
        }
    }
}