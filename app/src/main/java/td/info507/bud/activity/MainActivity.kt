package td.info507.bud.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import td.info507.bud.R
import td.info507.bud.adapter.CardAdapter
import td.info507.bud.dialog.dialogAdd

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Récupère RecyclerView
        val list_of_plant = findViewById<RecyclerView>(R.id.card_list_of_plants)

        // Set adapter avec listener
        list_of_plant.adapter = object : CardAdapter() {
            override fun onClickListener(view: View) {
                println("Item clicked") // Action clic sur un élément
            }
        }

        // Bouton flottant pour ajouter un nouvel élément
        findViewById<FloatingActionButton>(R.id.fab_add).setOnClickListener(){
            dialogAdd().show(supportFragmentManager, null)
        }
    }
}