package io.github.breadkey.pokemonchess.view.pokemonchess

import io.github.breadkey.pokemonchess.model.data.pokemon.PokemonSpec

interface PokemonForSaleListener {
    fun onClick(pokemonSpec: PokemonSpec)
}