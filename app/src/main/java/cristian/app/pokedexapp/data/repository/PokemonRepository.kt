package cristian.app.pokedexapp.data.repository

import cristian.app.pokedexapp.presentation.model.Pokemon
import cristian.app.pokedexapp.data.models.colors.SpeciesColor
import cristian.app.pokedexapp.data.models.details.PokemonDetails
import cristian.app.pokedexapp.data.remote.GETPokemonAPI
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val getPokemonAPI: GETPokemonAPI
) : IPokemonRepository {

    override suspend fun getListPokemons(): List<Pokemon> {
        try {
            val response = getPokemonAPI.getListPokemons()

            if (response.isSuccessful) {
                response.body()?.let { pokemonResponse ->
                    val listPokemonResult = pokemonResponse.results

                    val pokemons = listPokemonResult.map { pokemonResult ->
                        val number = pokemonResult.getNumberUrl()

                        getPokemonDetails(number).let { pokemonDetails ->
                            Pokemon(
                                pokemonDetails.id,
                                pokemonDetails.name,
                                pokemonDetails.types.map { it.type },
                                pokemonDetails.species,
                                pokemonDetails.height,
                                pokemonDetails.weight,
                                pokemonDetails.exp,
                                pokemonDetails.stats,
                                getPokemonColor(number).color.name
                            )
                        }
                    }
                    return pokemons
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception("Erro na requisição que lista os pokemons")
        }
        return emptyList()
    }

    override suspend fun getPokemonDetails(number: Int): PokemonDetails {
        try {
            val response = getPokemonAPI.getPokemonDetails(number)
            if (response.isSuccessful) {
                response.body()?.let { pokemonDetails ->
                    return pokemonDetails
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception("Erro na requisição detalhes dos pokemons")
        }
        throw Exception("pokemonDetail null")
    }

    override suspend fun getPokemonColor(number: Int): SpeciesColor {
        try {
            val response = getPokemonAPI.getColorPokemons(number)
            if (response.isSuccessful) {
                response.body()?.let { speciesColor ->
                    return speciesColor
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception("Erro na requisição detalhes para pegar cor pokemon")
        }
        throw Exception("speciesColor null")
    }
}