package io.github.breadkey.pokemonchess.model.data

import io.github.breadkey.pokemonchess.model.data.pokemon.PokemonSpec

data class Trainer(
    val id: Int,
    val name: String,
    val carriedPokemons: MutableList<PokemonSpec> = arrayListOf()
) {
    companion object {
        const val MAX_POKEMON_NUM_CAN_CARRY = 6
    }
}