package io.github.breadkey.pokemonchess.view.pokemonchess

import android.content.Context
import io.github.breadkey.pokemonchess.gameengine2d.GameScene
import io.github.breadkey.pokemonchess.gameengine2d.Vector3
import io.github.breadkey.pokemonchess.model.gameobject.BattleField
import io.github.breadkey.pokemonchess.model.gameobject.Tile
import io.github.breadkey.pokemonchess.model.gameobject.pokemon.Charizard
import io.github.breadkey.pokemonchess.model.gameobject.pokemon.Charmander

class PokemonChessScene(sceneName: String, context: Context): GameScene(sceneName, context) {
    override fun initializeScene() {
        camera.scale = Vector3(0.7f, 0.7f)

        val battleField = BattleField().apply {
            transform.position.y = Tile.Size
        }

        addGameObject(battleField)
        val charizard = Charizard()
        val charmander = Charmander()

        addGameObject(charizard)
        addGameObject(charmander)
        battleField.place(charizard, 0, 0)
        battleField.place(charmander, 0, 1)
    }
}