package io.github.breadkey.pokemonchess.gameengine2d

import android.content.Context
import io.github.breadkey.pokemonchess.R
import io.github.breadkey.pokemonchess.view.pokemonchess.PokemonChessScene

object GameSceneManager {
    var currentGameScene: GameScene? = null
    private val gameScenes = hashMapOf<String, GameScene>()

    fun loadScene(name: String, context: Context) {
        if (!gameScenes.keys.contains(name)) {
            when (name) {
                context.getString(R.string.pokemonChessScene) -> {
                    currentGameScene?.pause()
                    currentGameScene = PokemonChessScene(name, context)
                    with (currentGameScene!!) {
                        initializeScene()
                        play()
                    }
                    gameScenes[name] = currentGameScene!!
                }
            }
        } else {
            currentGameScene?.pause()
            currentGameScene = gameScenes[name]!!.apply {
                play()
            }
        }
    }
}