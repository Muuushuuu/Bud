package td.info507.bud.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import td.info507.bud.R
import td.info507.bud.adapter.CardMainAdapter
import td.info507.bud.dialog.DialogAdd
import td.info507.bud.dialog.DialogDel
import td.info507.bud.request.CardRequest
import td.info507.bud.storage.CardStorageMain
import td.info507.bud.storage.CardStorageSearch

class MainActivity : Updatable, AppCompatActivity() {

    companion object {
        const val EXTRA_CARD = "EXTRA_CARD"
    }

    private lateinit var list_of_plant: RecyclerView
    private lateinit var refresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Récupère RecyclerView
        list_of_plant = findViewById(R.id.card_list_of_plants)
        val cards = CardStorageMain.get(applicationContext).findAll()
        // Set adapter avec listener
        list_of_plant.adapter = object : CardMainAdapter(applicationContext, cards) {
            override fun onClickListener(view: View) {
                val cardId = view.tag as Int
                Log.d("MainActivity", "ID de la carte à envoyer : $cardId")

                val intent = Intent(applicationContext, EvolutionActivity::class.java).apply {
                    putExtra(EXTRA_CARD, cardId)
                }
                startActivity(intent)
            }

            override fun onLongClickListener(view: View): Boolean {
                val cardId = view.tag as Int
                DialogDel(cardId, applicationContext, this@MainActivity).show(supportFragmentManager, null)
                return true
            }
        }

        refresh = findViewById(R.id.card_refresh)
        refresh.setOnRefreshListener {
            CardRequest(applicationContext, this)
        }

        // Bouton flottant pour ajouter un nouvel élément
        findViewById<FloatingActionButton>(R.id.fab_add).setOnClickListener(){
            DialogAdd(this).show(supportFragmentManager, null)
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