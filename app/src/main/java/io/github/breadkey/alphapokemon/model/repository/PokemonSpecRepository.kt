package io.github.breadkey.alphapokemon.model.repository

import io.github.breadkey.alphapokemon.model.data.pokemon.PokemonSpec

interface PokemonSpecRepository {
    fun addPokemonSpec(pokemonSpec: PokemonSpec)

    fun deletePokemonSpec(pokemonSpec: PokemonSpec)

    fun updatePokemonSpec(pokemonSpec: PokemonSpec)

    fun findByIndexNumber(indexNumber: Int): List<PokemonSpec>

    fun findByName(name: String): List<PokemonSpec>
}