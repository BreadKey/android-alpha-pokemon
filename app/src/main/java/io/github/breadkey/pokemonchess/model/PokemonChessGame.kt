package io.github.breadkey.pokemonchess.model

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import io.github.breadkey.pokemonchess.model.data.Player
import io.github.breadkey.pokemonchess.model.data.Trainer
import io.github.breadkey.pokemonchess.model.data.pokemon.PokemonInChess
import io.github.breadkey.pokemonchess.model.data.pokemon.PokemonSpec

class PokemonChessGame {
    companion object {
        const val FILE_RANK_SIZE = 8
    }

    interface BuyPokemonCallback {
        fun onComplete(pokemonSpec: PokemonSpec, waitingPokemonsIndex: Int)
        fun onError(error: String)
    }

    val trainers: ObservableList<Trainer> = ObservableArrayList()
    val pokemonShop = LocalPokemonShop()

    fun startChessGame(trainers: List<Trainer>) {
        with (this.trainers) {
            clear()
            addAll(trainers)
        }
        pokemonShop.refreshPokemons()

        trainers.forEach { it.startGame() }
    }

    fun tryBuy(pokemonSpec: PokemonSpec, callback: BuyPokemonCallback) {
        for (index in 0 until Trainer.MAX_POKEMON_NUM_CAN_WAIT) {
            if (Player.waitingPokemons[index] == null) {
                val pokemonForSaleIndex = pokemonShop.pokemonsForSale.indexOf(pokemonSpec)
                pokemonShop.buy(pokemonForSaleIndex)
                callback.onComplete(pokemonSpec, index)
                return
            }
        }

        callback.onError("Waiting pokemons list is full")
    }
}