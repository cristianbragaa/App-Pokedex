package cristian.app.pokedexapp.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import cristian.app.pokedexapp.databinding.ItemPokemomBinding
import cristian.app.pokedexapp.presentation.model.Pokemon
import cristian.app.pokedexapp.presentation.ui.DetalhesActivity

class PokemonsAdapter(
    private val activity: Activity
) : RecyclerView.Adapter<PokemonsAdapter.PokemomViewHolder>() {

    private var listaPokemons = emptyList<Pokemon>()

    fun recuperarListaPokemons(pokemons: List<Pokemon>) {
        this.listaPokemons = pokemons
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemomViewHolder {
        val binding = ItemPokemomBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PokemomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemomViewHolder, position: Int) {
        val pokemon = listaPokemons[position]
        holder.bind(pokemon)
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = listaPokemons.count()

    inner class PokemomViewHolder(private val binding: ItemPokemomBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val nome = binding.textNome
        private val numero = binding.textNumber
        private val imagem = binding.imagePokemom
        private val natureza1 = binding.textNatureza1
        private val natureza2 = binding.textNatureza2
        private val progressBar = binding.progressItem

        fun bind(pokemon: Pokemon) {

            progressBar.visibility = View.VISIBLE
            Picasso.get().load(pokemon.imageUrl).into(imagem)
            progressBar.visibility = View.INVISIBLE

            numero.text = "Nº ${pokemon.numberFormat}"
            nome.text = pokemon.name.replaceFirstChar { firstCharacter ->
                firstCharacter.uppercase()
            }

            /* Seta nome da primeira natureza e cor de fundo que foi retornada */
            val firstTypeColor = Pokemon.getColorByType(pokemon.types[0].name)
            binding.textNatureza1.setBackgroundColor(ContextCompat.getColor(activity, firstTypeColor))
            natureza1.text = pokemon.types[0].name

            /* Caso tenha duas naturezas, é configurado junto com a cor de fundo */
            if (pokemon.types.size > 1) {
                val secondTypeColor = Pokemon.getColorByType(pokemon.types[1].name)
                binding.textNatureza2.setBackgroundColor(ContextCompat.getColor(activity, secondTypeColor))

                natureza2.text = pokemon.types[1].name
                natureza2.visibility = View.VISIBLE
            } else {
                natureza2.visibility = View.GONE
                binding.cardViewType2.visibility = View.GONE
            }

            /* Cor fundo da imagem principal baseada na cor predominante do pokemon */
            val backgroundColorPokemon = Pokemon.getBackgroundColorByPokemon(pokemon.color)
            binding.imagePokemom.setBackgroundColor(ContextCompat.getColor(activity, backgroundColorPokemon))

            /* Tranforma a lista de BaseStats nos valores de BaseStat, que seram usados na segunda tela */
            val valueStats = pokemon.stats.map { baseStat -> baseStat.valueBaseStat }
            itemView.setOnClickListener { view ->
                val intent = Intent(activity, DetalhesActivity::class.java)
                intent.putExtra("imagem", pokemon.imageUrl)
                intent.putExtra("color", pokemon.color)
                intent.putExtra("name", pokemon.name)
                intent.putExtra("number", pokemon.numberFormat)
                intent.putExtra("altura", pokemon.height.toString())
                intent.putExtra("peso", pokemon.weight.toString())
                intent.putExtra("exp", pokemon.exp)
                intent.putExtra("valueStat", valueStats.toIntArray())

                /* Transição baseada em um elemento view de item_pokemon */
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, binding.cardPrincipal,"imageTransition"
                )
                activity.startActivity(intent, options.toBundle())
            }
        }
    }
}

