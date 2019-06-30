package io.github.breadkey.pokemonchess.view.pokemonchess

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import io.github.breadkey.pokemonchess.gameengine2d.GameScene
import io.github.breadkey.pokemonchess.gameengine2d.Vector3
import io.github.breadkey.pokemonchess.model.PokemonChessGame
import io.github.breadkey.pokemonchess.model.data.Player
import io.github.breadkey.pokemonchess.model.data.Trainer
import io.github.breadkey.pokemonchess.model.data.pokemon.PokemonSpec
import io.github.breadkey.pokemonchess.model.gameobject.BattleField
import io.github.breadkey.pokemonchess.model.gameobject.Tile
import io.github.breadkey.pokemonchess.model.gameobject.pokemon.Charizard
import io.github.breadkey.pokemonchess.model.gameobject.pokemon.Charmander

@SuppressLint("ViewConstructor")
class PokemonChessScene(sceneName: String, context: Context): GameScene(sceneName, context) {
    lateinit var pokemonChessGame: PokemonChessGame
    val battleFields = hashMapOf<String, BattleField>()

    override fun initializeScene() {
        pokemonChessGame = PokemonChessGame()
        pokemonChessGame.startChessGame(listOf(Player))
        val battleField = BattleField(Player)
        battleFields[Player.id] = battleField

        addParentGameObject(battleField)
        camera.position = Vector3(battleField.transform.position)
    }

    fun tryBuy(pokemonSpec: PokemonSpec) {
        pokemonChessGame.tryBuy(pokemonSpec, object : PokemonChessGame.BuyPokemonCallback {
            override fun onComplete(pokemonSpec: PokemonSpec, waitingPokemonsIndex: Int) {
                val pokemonGameObject = Charmander()
                pokemonGameObject.owner = Player

                battleFields[Player.id]?.placeToWaitingTile(pokemonGameObject, waitingPokemonsIndex)
            }

            override fun onError(error: String) {
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            }
        })
    }
}