package io.github.breadkey.pokemonchess.model

import android.databinding.ObservableArrayList
import io.github.breadkey.pokemonchess.model.data.pokemon.PokemonSpec
import io.github.breadkey.pokemonchess.model.repository.LocalPokemonSpecRepo
import io.github.breadkey.pokemonchess.model.repository.PokemonSpecRepository

abstract class PokemonShop {
    val pokemonsForSale = ObservableArrayList<PokemonSpec>()
    abstract val pokemonSpecRepository: PokemonSpecRepository

    abstract fun refreshPokemons()
    abstract fun buy(index: Int)
    abstract fun sell(pokemonSpec: PokemonSpec)

    companion object {
        const val SALES_POKEMON_COUNT = 5
    }
}

class LocalPokemonShop: PokemonShop() {
    override val pokemonSpecRepository: PokemonSpecRepository = LocalPokemonSpecRepo

    override fun refreshPokemons() {
        pokemonsForSale.clear()
        pokemonsForSale.add(LocalPokemonSpecRepo.findByName("Charmander").first())
        pokemonsForSale.add(LocalPokemonSpecRepo.findByName("Charmander").first())
    }

    override fun buy(index: Int) {
        pokemonsForSale.removeAt(index)
    }

    override fun sell(pokemonSpec: PokemonSpec) {

    }
}