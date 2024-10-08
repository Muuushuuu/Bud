package td.info507.bud.modele

class Card(val id: Int, val nom: String, val description: String, val taille: String, val arrosage: String, val lumiere: String, val difficulte: String) {

    companion object {
        const val ID = "id"
        const val NOM = "nom"
        const val DESCRIPTION = "description"
        const val TAILLE = "taille"
        const val ARROSAGE = "arrosage"
        const val LUMIERE = "lumiere"
        const val DIFFICULTE = "difficulte"
    }
}