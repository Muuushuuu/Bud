package td.info507.bud.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import td.info507.bud.R
import td.info507.bud.adapter.CardSearchAdapter
import td.info507.bud.request.CardRequest
import td.info507.bud.storage.CardStorage

class SearchActivity : Updatable, AppCompatActivity() {


    private lateinit var list_of_plant: RecyclerView
    private lateinit var refresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // Récupère RecyclerView
        list_of_plant = findViewById(R.id.card_list_of_search_plants)
        val cards = CardStorage.get(applicationContext).findAll()
        CardRequest(applicationContext, this)
        // Set adapter avec listener
        list_of_plant.adapter = object : CardSearchAdapter(applicationContext, cards) {
            override fun onClickListener(view: View) {
                println("Item clicked") // Action clic sur un élément
            }
        }

        refresh = findViewById(R.id.card_search_refresh)
        refresh.setOnRefreshListener {
            CardRequest(applicationContext, this)
        }
    }

    override fun update() {
        list_of_plant.adapter?.notifyDataSetChanged()
        refresh.isRefreshing = false
    }
}