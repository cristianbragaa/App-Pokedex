package cristian.app.pokedexapp.data.models.list

data class PokemonResult(
    var name: String,
    var url: String
) {
    fun getNumberUrl() : Int {
        return this.url
            .replace("https://pokeapi.co/api/v2/pokemon/", "")
            .replace("/", "").toInt()
    }
}