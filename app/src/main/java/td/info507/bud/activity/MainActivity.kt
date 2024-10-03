package td.info507.bud.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import td.info507.bud.R
import td.info507.bud.dialog.CardAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val list_of_plant = findViewById<RecyclerView>(R.id.card_list_of_plant)
        list_of_plant.adapter = object : CardAdapter()*/


        // A FAIRE => quand on cliquera sur les éléments de la
        /* list_of_plant.adapter = object : CardAdapter() {
            override fun onClickListener(view: View) {
                val intent = Intent(applicationContext, CardActivity::class.java)
                startActivity(intent)
            }
        }*/

        /* findViewById<FloatingActionButton>(R.id.add_card).setOnClickListener(){
            CardDialogFragment().show(supportFragmentManager, null)
        }*/
    }
}