package td.info507.bud.storage

import td.info507.bud.modele.CardMainModel
import td.info507.bud.storage.utility.Storage

class CardNoneStorageMain : Storage<CardMainModel> {


    override fun insert(obj: CardMainModel): Int = 0

    override fun size(): Int = 0

    override fun find(id: Int): CardMainModel? = null

    override fun findAll(): List<CardMainModel> = emptyList()

    override fun update(id: Int, obj: CardMainModel) = Unit

    override fun delete(id: Int) = Unit
}
