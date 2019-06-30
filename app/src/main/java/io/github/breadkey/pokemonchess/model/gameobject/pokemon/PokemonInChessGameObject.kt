package io.github.breadkey.pokemonchess.model.gameobject.pokemon

import io.github.breadkey.pokemonchess.gameengine2d.BoxCollider
import io.github.breadkey.pokemonchess.gameengine2d.TouchableObject
import io.github.breadkey.pokemonchess.gameengine2d.Vector3
import io.github.breadkey.pokemonchess.model.data.Player
import io.github.breadkey.pokemonchess.model.data.Trainer
import io.github.breadkey.pokemonchess.model.data.pokemon.PokemonInChess
import io.github.breadkey.pokemonchess.model.gameobject.BattleField
import io.github.breadkey.pokemonchess.model.gameobject.Tile

open class PokemonInChessGameObject(val pokemonInChess: PokemonInChess): TouchableObject(
    touchCollider = BoxCollider(
        offset = Vector3(0f, 0f),
        size = Vector3(Tile.Size, Tile.Size)
    )
) {
    private lateinit var previousPosition: Vector3
    private lateinit var previousScale: Vector3
    var owner: Trainer? = null
        set(value) {
            pokemonInChess.trainer = value
            field = value
        }

    override fun touched() {
        if (owner != Player) return

        previousPosition = Vector3(transform.position)
        previousScale = Vector3(transform.scale)
        transform.scale = previousScale * 1.5f
    }

    override fun moved(x: Float, y: Float) {
        if (owner != Player) return

        val globalTransform = globalTransform
        val realOffset = touchCollider.offset * touchCollider.size * globalTransform.scale

        transform.position.x = x - realOffset.x
        if (parent != null) transform.position.x -= parent!!.globalTransform.position.x
        transform.position.y = y - realOffset.y
        if (parent != null) transform.position.y -= parent!!.globalTransform.position.y
    }

    override fun released(x: Float, y: Float) {
        if (owner != Player) return

        transform.scale = previousScale
        with (parent as? BattleField) {
            if (this?.place(this@PokemonInChessGameObject) == false) {
                if (this?.placeToWaitingTile(this@PokemonInChessGameObject) == false) {
                    this@PokemonInChessGameObject.transform.position = previousPosition
                }
            }
        }
    }
}