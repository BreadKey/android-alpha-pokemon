package io.github.breadkey.pokemonchess.view.pokemonchess

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.breadkey.pokemonchess.viewmodel.PokemonChessViewModel
import io.github.breadkey.pokemonchess.databinding.PokemonForSaleBinding
import io.github.breadkey.pokemonchess.model.data.pokemon.PokemonSpec
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PokemonForSaleAdapter(private val viewModel: PokemonChessViewModel): RecyclerView.Adapter<PokemonForSaleAdapter.PokemonViewHolder>() {
    var pokemonSpecs = emptyList<PokemonSpec>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PokemonForSaleBinding.inflate(layoutInflater, parent, false)

        return PokemonViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return pokemonSpecs.count()
    }

    override fun onBindViewHolder(viewHolder: PokemonViewHolder, position: Int) {
        with(viewHolder.binding) {
            pokemonSpec = pokemonSpecs[position]
            listener = object : PokemonForSaleListener {
                override fun onClick(pokemonSpec: PokemonSpec) {
                    GlobalScope.launch {
                        viewModel.tryBuy(pokemonSpec)
                    }
                }
            }
            executePendingBindings()
        }
    }


    class PokemonViewHolder(var binding: PokemonForSaleBinding): RecyclerView.ViewHolder(binding.root)
}