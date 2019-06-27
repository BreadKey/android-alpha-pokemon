package io.github.breadkey.pokemonchess.view.pokemonchess

import android.content.Context
import io.github.breadkey.pokemonchess.gameengine2d.GameScene
import io.github.breadkey.pokemonchess.gameengine2d.Vector3
import io.github.breadkey.pokemonchess.model.gameobject.pokemon.Charmander

class PokemonChessScene(sceneName: String, context: Context): GameScene(sceneName, context) {
    init {
        camera.position = Vector3(100f, 100f, 0f)
        gameObjects.add(Charmander().apply {
            addChild(Charmander().apply {
                transform.position = Vector3(150f, 150f, 0f)
            })
        })
    }
}