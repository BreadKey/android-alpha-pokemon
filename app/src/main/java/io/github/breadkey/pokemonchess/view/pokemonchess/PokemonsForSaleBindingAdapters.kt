package io.github.breadkey.pokemonchess.view.pokemonchess

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import io.github.breadkey.pokemonchess.model.data.pokemon.PokemonSpec

object PokemonsForSaleBindingAdapters {
    @BindingAdapter("sothree:pokemon_specs")
    @JvmStatic fun setItems(view: RecyclerView, items: List<PokemonSpec>) {
        with(view.adapter as PokemonForSaleAdapter) {
            pokemonSpecs = items
        }
    }
}