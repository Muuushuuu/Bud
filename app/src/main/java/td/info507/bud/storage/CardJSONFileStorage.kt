package td.info507.bud.storage

import android.content.Context
import org.json.JSONObject
import td.info507.bud.modele.CardSearchModel
import td.info507.bud.storage.utility.Storage
import td.info507.bud.storage.utility.file.JSONFileStorage

class CardJSONFileStorage(context: Context) : JSONFileStorage<CardSearchModel>(context, "card"),
    Storage<CardSearchModel> {

    override fun create(id: Int, obj: CardSearchModel): CardSearchModel {
        return CardSearchModel(id, obj.nom, obj.description, obj.taille, obj.arrosage, obj.lumiere, obj.difficulte, obj.image)
    }

    override fun objectToJson(id: Int, obj: CardSearchModel): JSONObject {
        val json = JSONObject()
        json.put(CardSearchModel.ID, obj.id)
        json.put(CardSearchModel.NOM, obj.nom)
        json.put(CardSearchModel.DESCRIPTION, obj.description)
        json.put(CardSearchModel.TAILLE, obj.taille)
        json.put(CardSearchModel.ARROSAGE, obj.arrosage)
        json.put(CardSearchModel.LUMIERE, obj.lumiere)
        json.put(CardSearchModel.DIFFICULTE, obj.difficulte)
        json.put(CardSearchModel.IMAGE, obj.image)
        return json
    }

    override fun jsonToObject(json: JSONObject): CardSearchModel {
        return CardSearchModel(
            json.getInt(CardSearchModel.ID),
            json.getString(CardSearchModel.NOM),
            json.getString(CardSearchModel.DESCRIPTION),
            json.getString(CardSearchModel.TAILLE),
            json.getString(CardSearchModel.ARROSAGE),
            json.getString(CardSearchModel.LUMIERE),
            json.getString(CardSearchModel.DIFFICULTE),
            json.optString(CardSearchModel.IMAGE, "") // Utilisation correcte de optString()
        )
    }
}