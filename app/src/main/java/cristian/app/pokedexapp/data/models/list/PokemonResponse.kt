package cristian.app.pokedexapp.data.models.list

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonResult>
)