package cristian.app.pokedexapp.data.models.details

import com.google.gson.annotations.SerializedName

data class BaseStat(
    @SerializedName("base_stat")
    val valueBaseStat: Int
)
