package cristian.app.pokedexapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.skydoves.progressview.ProgressView
import com.skydoves.progressview.ProgressViewAnimation
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import cristian.app.pokedexapp.databinding.ActivityDetalhesBinding
import cristian.app.pokedexapp.presentation.model.Pokemon
import java.lang.Exception

class DetalhesActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetalhesBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(binding.root)
        supportActionBar?.hide()

        ibButtonBack()
        getBundle()
    }

    private fun getBundle() {
        intent.extras?.let { bundle ->
            val image = bundle.getString("imagem") ?: ""
            val color = bundle.getString("color") ?: ""
            val name = bundle.getString("name") ?: ""
            val number = bundle.getString("number") ?: ""
            val height = bundle.getString("altura") ?: ""
            val weight = bundle.getString("peso") ?: ""
            val exp = bundle.getInt("exp")
            val valueStat = bundle.getIntArray("valueStat")

            /* Config Infos */
            supportPostponeEnterTransition()
            Picasso.get().load(image).into(binding.imageDetalhe, object : Callback {
                override fun onSuccess() {
                    supportStartPostponedEnterTransition()
                }

                override fun onError(e: Exception?) {
                    Log.e("ERROR_LOADING_IMAGE", "Exception image loading. Message -> ${e?.message}")
                    e?.printStackTrace()
                }
            })

            with(binding) {
                val backgroundColorPokemon = Pokemon.getBackgroundColorByPokemon(color)
                constraintRootDetailPokemon.setBackgroundColor(
                    ContextCompat.getColor(applicationContext, backgroundColorPokemon)
                )
                textNameDetalhe.text = name.replaceFirstChar { it.uppercase() }
                textNumberDetalhe.text = "Nº $number"

                textWeightDetail.text = "$weight KG"
                textHeightDetail.text = "$height M"

                /*val animation = ProgressViewAnimation.DECELERATE
                 binding.progressViewEXP.progressAnimation = animation*/
                /* Configuring Progress Views */
                progressViewEXP.progress = exp.toFloat()
                progressViewEXP.labelText = "$exp/1000"

                val valueHp = valueStat?.get(0)
                progressViewHP.progress = valueHp.toString().toFloat()
                progressViewHP.labelText = "$valueHp/300"

                val valueAtk = valueStat?.get(1)
                progressViewATK.progress = valueAtk.toString().toFloat()
                progressViewATK.labelText = "$valueAtk/300"

                val valueDef = valueStat?.get(2)
                progressViewDEF.progress = valueDef.toString().toFloat()
                progressViewDEF.labelText = "$valueDef/300"

                val valueSpd = valueStat?.get(5)
                progressViewSPD.progress = valueSpd.toString().toFloat()
                progressViewSPD.labelText = "$valueSpd/300"
            }
        }
    }

    private fun ibButtonBack() {
        binding.ibArrowBack.setOnClickListener {
            supportFinishAfterTransition()
            finish()
        }
    }

    private fun installSplash() {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition(SplashScreen.KeepOnScreenCondition {
            return@KeepOnScreenCondition false
        })
    }

}
