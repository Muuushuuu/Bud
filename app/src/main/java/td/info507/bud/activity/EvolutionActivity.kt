package td.info507.bud.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import td.info507.bud.R
import td.info507.bud.adapter.CardEvolutionAdapter
import td.info507.bud.modele.CardMainModel
import td.info507.bud.modele.CardEvolutionModel
import td.info507.bud.storage.CardStorageEvolution
import td.info507.bud.storage.CardStorageMain
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EvolutionActivity: AppCompatActivity() {
    private lateinit var card: CardMainModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CardEvolutionAdapter
    private lateinit var evolutionList: MutableList<CardEvolutionModel>

    private val takePhoto = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        if (bitmap != null) {
            val plantId = card.id
            val photoFile = saveImageToInternalStorage(bitmap, plantId)
            if (photoFile != null) {
                Log.d("EvolutionActivity", "Photo enregistrée : ${photoFile.absolutePath}")

                val timeStamp: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

                // Enregistrer l'évolution via le storage
                val storage = CardStorageEvolution.get(applicationContext, plantId) // Corrige ici
                val newEvolution = CardEvolutionModel(0, timeStamp, photoFile.absolutePath)
                storage.insert(newEvolution)

                // Recharger les données et mettre à jour la RecyclerView
                reloadEvolutionData(plantId)
            } else {
                Log.d("EvolutionActivity", "Erreur lors de la sauvegarde de la photo.")
            }
        } else {
            Log.d("EvolutionActivity", "Photo capture failed.")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evolution)

        val cardId = intent.getIntExtra(MainActivity.EXTRA_CARD, 0)
        card = CardStorageMain.get(applicationContext).find(cardId) ?: return

        findViewById<TextView>(R.id.evolution_title).text = "Evolution de " + card.nom
        findViewById<TextView>(R.id.evolution_desc).text = card.description
        findViewById<TextView>(R.id.evolution_taille).text = card.taille
        findViewById<TextView>(R.id.evolution_arrosage).text = card.arrosage
        findViewById<TextView>(R.id.evolution_lumiere).text = card.lumiere
        findViewById<TextView>(R.id.evolution_difficulte).text = card.difficulte

        recyclerView = findViewById(R.id.recyclerView_horizontal)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        // Charger les données depuis le storage
        reloadEvolutionData(card.id)

        val pictureButton = findViewById<FloatingActionButton>(R.id.item_add_picture)
        pictureButton.setOnClickListener {
            takePhoto.launch(null)
        }
    }

    // Sauvegarder l'image dans le dossier spécifique lié à la plante
    private fun saveImageToInternalStorage(bitmap: Bitmap, plantId: Int): File? {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "IMG_$timeStamp.jpg"
        val storageDir: File = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "plant_$plantId")
        if (!storageDir.exists()) {
            storageDir.mkdirs()
        }

        val imageFile = File(storageDir, fileName)
        return try {
            val fos = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
            imageFile
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    // Recharger les données d'évolution depuis le storage
    private fun reloadEvolutionData(plantId: Int) {
        val storage = CardStorageEvolution.get(applicationContext, plantId)
        evolutionList = storage.findAll().toMutableList()

        // Créer l'adapter en définissant l'implémentation de onClickListener
        recyclerView.adapter = object : CardEvolutionAdapter(applicationContext, plantId, evolutionList) {
            override fun onClickListener(view: View) {
                // Tu peux laisser cette méthode vide si tu n'as pas de clic spécifique à gérer
            }
        }
    }
}