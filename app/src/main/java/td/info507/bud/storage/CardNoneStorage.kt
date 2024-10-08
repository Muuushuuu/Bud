package td.info507.bud.storage

import td.info507.bud.modele.Card
import td.info507.bud.storage.utility.Storage

class CardNoneStorage : Storage<Card> {


    override fun insert(obj: Card): Int = 0

    override fun size(): Int = 0

    override fun find(id: Int): Card? = null

    override fun findAll(): List<Card> = emptyList()

    override fun update(id: Int, obj: Card) = Unit

    override fun delete(id: Int) = Unit
}
