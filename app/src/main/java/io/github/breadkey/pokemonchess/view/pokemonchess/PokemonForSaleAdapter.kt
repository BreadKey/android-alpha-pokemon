package io.github.breadkey.pokemonchess.view.pokemonchess

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.breadkey.pokemonchess.R
import io.github.breadkey.pokemonchess.viewmodel.PokemonChessViewModel
import io.github.breadkey.pokemonchess.databinding.PokemonForSaleBinding
import io.github.breadkey.pokemonchess.gameengine2d.CharmanderSprites
import io.github.breadkey.pokemonchess.gameengine2d.PokemonSprites
import io.github.breadkey.pokemonchess.model.PokemonShop
import io.github.breadkey.pokemonchess.model.data.pokemon.PokemonSpec
import kotlinx.coroutines.*

class PokemonForSaleAdapter(private val viewModel: PokemonChessViewModel): RecyclerView.Adapter<PokemonForSaleAdapter.PokemonViewHolder>() {
    var pokemonSpecs = emptyList<PokemonSpec>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PokemonForSaleBinding.inflate(layoutInflater, parent, false)
        val width = parent.measuredWidth / PokemonShop.SALES_POKEMON_COUNT
        val height = width * 8 / 10

        binding.buyButtonContainer.layoutParams = (RecyclerView.LayoutParams(width, height))

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
            pokemonCostText.text = pokemonSpec!!.cost.toString()
            listener = object : PokemonForSaleListener {
                override fun onClick(pokemonSpec: PokemonSpec) {
                    viewModel.tryBuy(pokemonSpec)
                }
            }

            when (pokemonSpec!!.cost) {
                1 -> buyButton.setBackgroundResource(R.drawable.poke_ball_background)
            }

            when (pokemonSpec!!.name) {
                "Charmander" -> pokemonForSaleImage.setImageBitmap(CharmanderSprites.frontIdle[0].bitmap)
            }

            executePendingBindings()
        }
    }


    class PokemonViewHolder(var binding: PokemonForSaleBinding): RecyclerView.ViewHolder(binding.root)
}