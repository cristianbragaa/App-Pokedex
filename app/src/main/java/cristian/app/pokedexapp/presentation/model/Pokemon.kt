package cristian.app.pokedexapp.presentation.model

import com.google.gson.annotations.SerializedName
import cristian.app.pokedexapp.R
import cristian.app.pokedexapp.data.models.colors.SpeciesResponse
import cristian.app.pokedexapp.data.models.details.BaseStat
import cristian.app.pokedexapp.data.models.details.Type

data class Pokemon(
    var number: Int,
    var name: String,
    var types: List<Type>,
    var species: SpeciesResponse,
    val height: Int,
    val weight: Int,
    @SerializedName("base_experience")
    val exp: Int,
    val stats: List<BaseStat>,
    var color: String
) {
    val numberFormat = number.toString().padStart(3, '0')
    val imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/$numberFormat.png"

    companion object {

        fun getColorByType(nameType: String): Int {
            return when (nameType) {
                "normal" -> R.color.fundo_tipo_normal
                "fire" -> R.color.fundo_tipo_fire
                "water" -> R.color.fundo_tipo_water
                "grass" -> R.color.fundo_tipo_grass
                "flying" -> R.color.fundo_tipo_flying
                "fighting" -> R.color.fundo_tipo_fighting
                "poison" -> R.color.fundo_tipo_poison
                "electric" -> R.color.fundo_tipo_electric
                "ground" -> R.color.fundo_tipo_ground
                "rock" -> R.color.fundo_tipo_rock
                "psychic" -> R.color.fundo_tipo_psychic
                "ice" -> R.color.fundo_tipo_ice
                "bug" -> R.color.fundo_tipo_bug
                "ghost" -> R.color.fundo_tipo_ghost
                "steel" -> R.color.fundo_tipo_steel
                "dragon" -> R.color.fundo_tipo_dragon
                "dark" -> R.color.fundo_tipo_dark
                "fairy" -> R.color.fundo_tipo_fairy
                else -> R.color.fundo_cinza_claro
            }
        }

        fun getBackgroundColorByPokemon(nameColorBackground: String): Int {
            return when (nameColorBackground) {
                "red" -> R.color.fundo_red
                "blue" -> R.color.fundo_blue
                "green" -> R.color.fundo_green
                "yellow" -> R.color.fundo_yellow
                "purple" -> R.color.fundo_purple
                "pink" -> R.color.fundo_pink
                "brown" -> R.color.fundo_brown
                "black" -> R.color.fundo_black
                "gray" -> R.color.fundo_gray
                "white" -> R.color.fundo_cinza_claro
                else -> R.color.fundo_cinza_claro
            }
        }
    }
}
