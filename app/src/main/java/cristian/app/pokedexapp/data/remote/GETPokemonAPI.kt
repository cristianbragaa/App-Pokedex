package cristian.app.pokedexapp.data.remote

import cristian.app.pokedexapp.data.models.colors.SpeciesColor
import cristian.app.pokedexapp.data.models.details.PokemonDetails
import cristian.app.pokedexapp.data.models.list.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GETPokemonAPI {

    @GET("pokemon/?limit=100")
    suspend fun getListPokemons(
    ): Response<PokemonResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(
        @Path("id") number: Int
    ): Response<PokemonDetails>

    @GET("pokemon-species/{id}/")
    suspend fun getColorPokemons(
        @Path("id") number: Int
    ): Response<SpeciesColor>

}