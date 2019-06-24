package io.github.breadkey.pokemonchess.view.pokemonchess

import android.content.Context
import android.os.Handler
import io.github.breadkey.pokemonchess.gameengine2d.GameScene
import io.github.breadkey.pokemonchess.model.data.pokemon.gameObject.Charmander

class PokemonChessScene(context: Context): GameScene(context) {
    init {
        gameObjects.add(Charmander())
        play()
    }
}