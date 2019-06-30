package io.github.breadkey.pokemonchess.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import io.github.breadkey.pokemonchess.R
import io.github.breadkey.pokemonchess.gameengine2d.GameScene
import io.github.breadkey.pokemonchess.gameengine2d.GameSceneManager
import io.github.breadkey.pokemonchess.model.data.pokemon.PokemonSpec
import io.github.breadkey.pokemonchess.view.pokemonchess.PokemonChessScene
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class PokemonChessViewModel(app: Application): AndroidViewModel(app) {
    @SuppressLint("StaticFieldLeak")
    val context = app.applicationContext
    @SuppressLint("StaticFieldLeak")
    val pokemonChessScene: PokemonChessScene

    init {
        GameSceneManager.loadScene(app.getString(R.string.pokemonChessScene), context)
        pokemonChessScene = (GameSceneManager.currentGameScene as PokemonChessScene?)!!
    }

    fun tryBuy(pokemonSpec: PokemonSpec) = runBlocking {
        GlobalScope.launch {
            withContext(pokemonChessScene.updateContext) {
                pokemonChessScene.tryBuy(pokemonSpec)
            }
        }
    }
}