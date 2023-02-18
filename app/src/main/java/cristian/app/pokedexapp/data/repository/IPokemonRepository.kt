package cristian.app.pokedexapp.data.repository

import cristian.app.pokedexapp.presentation.model.Pokemon
import cristian.app.pokedexapp.data.models.colors.SpeciesColor
import cristian.app.pokedexapp.data.models.details.PokemonDetails

interface IPokemonRepository {
    suspend fun getListPokemons(): List<Pokemon>
    suspend fun getPokemonDetails(number: Int): PokemonDetails
    suspend fun getPokemonColor(number: Int): SpeciesColor
}