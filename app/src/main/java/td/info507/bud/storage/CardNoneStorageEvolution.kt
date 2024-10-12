package td.info507.bud.storage

import td.info507.bud.modele.CardEvolutionModel
import td.info507.bud.storage.utility.Storage

class CardNoneStorageEvolution : Storage<CardEvolutionModel> {


    override fun insert(obj: CardEvolutionModel): Int = 0

    override fun size(): Int = 0

    override fun find(id: Int): CardEvolutionModel? = null

    override fun findAll(): List<CardEvolutionModel> = emptyList()

    override fun update(id: Int, obj: CardEvolutionModel) = Unit

    override fun delete(id: Int) = Unit
}
