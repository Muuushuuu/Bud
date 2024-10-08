package td.info507.bud.storage

import android.content.Context
import org.json.JSONObject
import td.info507.bud.modele.Card
import td.info507.bud.storage.utility.Storage
import td.info507.bud.storage.utility.file.JSONFileStorage

class CardJSONFileStorage(context: Context) : JSONFileStorage<Card>(context, "card"),
    Storage<Card> {

    override fun create(id: Int, obj: Card): Card {
        return Card(id, obj.nom, obj.description, obj.taille, obj.arrosage, obj.lumiere, obj.difficulte)
    }

    override fun objectToJson(id: Int, obj: Card): JSONObject {
        val json = JSONObject()
        json.put(Card.ID, obj.id)
        json.put(Card.NOM, obj.nom)
        json.put(Card.DESCRIPTION, obj.description)
        json.put(Card.TAILLE, obj.taille)
        json.put(Card.ARROSAGE, obj.arrosage)
        json.put(Card.LUMIERE, obj.lumiere)
        json.put(Card.DIFFICULTE, obj.difficulte)
        return json
    }

    override fun jsonToObject(json: JSONObject): Card {
        return Card(
            json.getInt(Card.ID),
            json.getString(Card.NOM),
            json.getString(Card.DESCRIPTION),
            json.getString(Card.TAILLE),
            json.getString(Card.ARROSAGE),
            json.getString(Card.LUMIERE),
            json.getString(Card.DIFFICULTE),
        )
    }
}