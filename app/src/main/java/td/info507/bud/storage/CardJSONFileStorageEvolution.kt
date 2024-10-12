package td.info507.bud.storage

import android.content.Context
import org.json.JSONObject
import td.info507.bud.modele.CardEvolutionModel
import td.info507.bud.storage.utility.Storage
import td.info507.bud.storage.utility.file.JSONFileStorage

class CardJSONFileStorageEvolution(context: Context, plantId: Int) : JSONFileStorage<CardEvolutionModel>(context, "plantes_$plantId"),
    Storage<CardEvolutionModel> {

    override fun create(id: Int, obj: CardEvolutionModel): CardEvolutionModel {
        return CardEvolutionModel(id, obj.date, obj.img_src)
    }

    override fun objectToJson(id: Int, obj: CardEvolutionModel): JSONObject {
        val json = JSONObject()
        json.put(CardEvolutionModel.ID, obj.id)
        json.put(CardEvolutionModel.DATE, obj.date)
        json.put(CardEvolutionModel.IMG_SRC, obj.img_src)
        return json
    }

    override fun jsonToObject(json: JSONObject): CardEvolutionModel {
        return CardEvolutionModel(
            json.getInt(CardEvolutionModel.ID),
            json.optString(CardEvolutionModel.DATE, "Unknown Date"),
            json.optString(CardEvolutionModel.IMG_SRC, "")
        )
    }
}