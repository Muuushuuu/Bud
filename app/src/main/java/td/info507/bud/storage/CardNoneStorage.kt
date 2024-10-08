package td.info507.bud.storage

import td.info507.bud.modele.CardSearchModel
import td.info507.bud.storage.utility.Storage

class CardNoneStorage : Storage<CardSearchModel> {


    override fun insert(obj: CardSearchModel): Int = 0

    override fun size(): Int = 0

    override fun find(id: Int): CardSearchModel? = null

    override fun findAll(): List<CardSearchModel> = emptyList()

    override fun update(id: Int, obj: CardSearchModel) = Unit

    override fun delete(id: Int) = Unit
}
