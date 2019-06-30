package io.github.breadkey.pokemonchess.model.data

import android.databinding.ObservableField
import io.github.breadkey.pokemonchess.model.PokemonChessGame
import io.github.breadkey.pokemonchess.model.data.pokemon.PokemonInChess

open class Trainer(
    val id: String,
    val name: String
) {
    var hp = ObservableField(MAX_HP)
    val waitingPokemons = arrayListOf<PokemonInChess?>().apply {
        for (index in 0 until MAX_POKEMON_NUM_CAN_WAIT) {
            this.add(null)
        }
    }
    val pokemonsInChessBoard = arrayListOf<ArrayList<PokemonInChess?>>().apply {
        for (rank in 0 until PokemonChessGame.FILE_RANK_SIZE) {
            val rankList = arrayListOf<PokemonInChess?>()
            for (file in 0 until PokemonChessGame.FILE_RANK_SIZE) {
                rankList.add(null)
            }
            this.add(rankList)
        }
    }

    fun startGame() {
        hp.set(MAX_HP)
        clearWaitingPokemons()
        clearPokemonsInField()
    }

    private fun clearWaitingPokemons() {
        for (index in 0 until MAX_POKEMON_NUM_CAN_WAIT) {
            waitingPokemons[index] = null
        }
    }

    private fun clearPokemonsInField() {
        for (file in 0 until PokemonChessGame.FILE_RANK_SIZE) {
            for (rank in 0 until PokemonChessGame.FILE_RANK_SIZE) {
                pokemonsInChessBoard[file][rank] = null
            }
        }
    }

    companion object {
        const val MAX_HP = 100
        const val MAX_POKEMON_NUM_CAN_WAIT = 8
    }
}