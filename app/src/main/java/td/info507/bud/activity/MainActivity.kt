package td.info507.bud.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import td.info507.bud.R
import td.info507.bud.adapter.CardAdapter
import td.info507.bud.dialog.dialogAdd
import td.info507.bud.request.CardRequest

class MainActivity : Updatable, AppCompatActivity() {

    private lateinit var list_of_plant: RecyclerView
    private lateinit var refresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Récupère RecyclerView
        list_of_plant = findViewById(R.id.card_list_of_plants)

        // Set adapter avec listener
        list_of_plant.adapter = object : CardAdapter() {
            override fun onClickListener(view: View) {
                println("Item clicked") // Action clic sur un élément
            }
        }

        refresh = findViewById(R.id.card_refresh)
        refresh.setOnRefreshListener {
            CardRequest(applicationContext, this)
        }

        // Bouton flottant pour ajouter un nouvel élément
        findViewById<FloatingActionButton>(R.id.fab_add).setOnClickListener(){
            dialogAdd().show(supportFragmentManager, null)
        }
    }

    override fun update() {
        list_of_plant.adapter?.notifyDataSetChanged()
        refresh.isRefreshing = false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.flower -> startActivity(Intent(applicationContext, SearchActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}