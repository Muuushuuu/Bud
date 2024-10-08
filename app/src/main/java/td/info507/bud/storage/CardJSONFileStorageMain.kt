package td.info507.bud.storage

import android.content.Context
import org.json.JSONObject
import td.info507.bud.modele.CardMainModel
import td.info507.bud.storage.utility.Storage
import td.info507.bud.storage.utility.file.JSONFileStorage

class CardJSONFileStorageMain(context: Context) : JSONFileStorage<CardMainModel>(context, "plantes"),
    Storage<CardMainModel> {

    override fun create(id: Int, obj: CardMainModel): CardMainModel {
        return CardMainModel(id, obj.nom, obj.type, obj.description, obj.taille, obj.arrosage, obj.lumiere, obj.difficulte, obj.image)
    }

    override fun objectToJson(id: Int, obj: CardMainModel): JSONObject {
        val json = JSONObject()
        json.put(CardMainModel.ID, obj.id)
        json.put(CardMainModel.NOM, obj.nom)
        json.put(CardMainModel.TYPE, obj.type)
        json.put(CardMainModel.DESCRIPTION, obj.description)
        json.put(CardMainModel.TAILLE, obj.taille)
        json.put(CardMainModel.ARROSAGE, obj.arrosage)
        json.put(CardMainModel.LUMIERE, obj.lumiere)
        json.put(CardMainModel.DIFFICULTE, obj.difficulte)
        json.put(CardMainModel.IMAGE, obj.image)
        return json
    }

    override fun jsonToObject(json: JSONObject): CardMainModel {
        return CardMainModel(
            json.getInt(CardMainModel.ID),
            json.getString(CardMainModel.NOM),
            json.getString(CardMainModel.TYPE),
            json.getString(CardMainModel.DESCRIPTION),
            json.getString(CardMainModel.TAILLE),
            json.getString(CardMainModel.ARROSAGE),
            json.getString(CardMainModel.LUMIERE),
            json.getString(CardMainModel.DIFFICULTE),
            json.optString(CardMainModel.IMAGE, "")
        )
    }
}