package cristian.app.pokedexapp.presentation.ui

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.GridLayoutManager
import cristian.app.pokedexapp.adapter.PokemonsAdapter
import cristian.app.pokedexapp.databinding.ActivityMainBinding
import cristian.app.pokedexapp.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var adapterPokemom: PokemonsAdapter
    private lateinit var progressBar: ProgressBar
    private var job: Job? = null

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(binding.root)
        supportActionBar?.hide()

        adapterPokemom = PokemonsAdapter(this)
        with(binding) {
            rvPokemons.adapter = adapterPokemom
            rvPokemons.setHasFixedSize(true)
            rvPokemons.layoutManager = GridLayoutManager(applicationContext, 2)
        }

        initObserver()
    }

    private fun initObserver() {
        progressBar = binding.progressPrincipal

        viewModel.listaPokemons.observe(this) { listPokemons ->
            job = CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    adapterPokemom.recuperarListaPokemons(listPokemons)
                }
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun installSplash() {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition(SplashScreen.KeepOnScreenCondition {
            Thread.sleep(2000L)
            return@KeepOnScreenCondition false
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}

