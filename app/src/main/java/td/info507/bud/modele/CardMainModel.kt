package td.info507.bud.modele

class CardMainModel (val id: Int, val nom: String, val type: String, val description: String, val taille: String, val arrosage: String, val lumiere: String, val difficulte: String, val image: String) {
    companion object {
        const val ID = "id"
        const val NOM = "nom"
        const val TYPE = "type"
        const val DESCRIPTION = "description"
        const val TAILLE = "taille"
        const val ARROSAGE = "arrosage"
        const val LUMIERE = "lumiere"
        const val DIFFICULTE = "difficulte"
        const val IMAGE = "image"
    }
}