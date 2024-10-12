package td.info507.bud.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import td.info507.bud.R
import td.info507.bud.adapter.CardEvolutionAdapter
import td.info507.bud.modele.CardMainModel
import td.info507.bud.storage.CardStorageMain

class EvolutionActivity: AppCompatActivity() {
    private lateinit var card: CardMainModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CardEvolutionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evolution)

        // Récupérer l'ID de la carte depuis l'Intent
        val cardId = intent.getIntExtra(MainActivity.EXTRA_CARD, 0)
        Log.d("CardActivity", "ID de la carte reçue : $cardId")

        card = CardStorageMain.get(applicationContext).find(cardId) ?: return

        findViewById<TextView>(R.id.evolution_title).text = "Evolution de " + card.nom
        findViewById<TextView>(R.id.evolution_desc).text = card.description
        findViewById<TextView>(R.id.evolution_taille).text = card.taille
        findViewById<TextView>(R.id.evolution_arrosage).text = card.arrosage
        findViewById<TextView>(R.id.evolution_lumiere).text = card.lumiere
        findViewById<TextView>(R.id.evolution_difficulte).text = card.difficulte

        recyclerView = findViewById(R.id.recyclerView_horizontal)

        // Configurer le LinearLayoutManager en mode horizontal
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        // Définir l'adapter pour la RecyclerView
        adapter = CardEvolutionAdapter()
        recyclerView.adapter = adapter



    }

//    private fun addNewPicture() {
//        var picture_button = findViewById<FloatingActionButton>(R.id.item_add_picture)
//
//        var take_photo =
//            registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
//                if (bitmap != null)
//
//            }
//
//        picture_button.setOnClickListener {
//            take_photo.launch(null)
//        }
//    }


}