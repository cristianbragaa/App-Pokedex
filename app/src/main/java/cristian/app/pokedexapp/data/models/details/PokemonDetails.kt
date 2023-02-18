package cristian.app.pokedexapp.data.models.details

import com.google.gson.annotations.SerializedName
import cristian.app.pokedexapp.data.models.colors.SpeciesResponse

data class PokemonDetails(
    val id: Int,
    val name: String,
    val types: List<Types>,
    val species: SpeciesResponse,
    val height: Int,
    val weight: Int,
    @SerializedName("base_experience")
    val exp: Int,
    val stats: List<BaseStat>
)

