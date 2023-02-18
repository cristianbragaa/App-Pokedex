package cristian.app.pokedexapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cristian.app.pokedexapp.presentation.model.Pokemon
import cristian.app.pokedexapp.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val liveData = MutableLiveData<List<Pokemon>>()

    val listaPokemons: MutableLiveData<List<Pokemon>>
        get() = liveData

    init {
        getListPokemons()
    }

    private fun getListPokemons() {
        viewModelScope.launch(Dispatchers.IO) {
            liveData.postValue(
                pokemonRepository.getListPokemons()
            )
        }
    }
}