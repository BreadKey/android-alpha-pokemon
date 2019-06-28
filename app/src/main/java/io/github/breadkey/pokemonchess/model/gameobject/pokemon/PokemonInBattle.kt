package io.github.breadkey.pokemonchess.model.gameobject.pokemon

import io.github.breadkey.pokemonchess.gameengine2d.BoxCollider
import io.github.breadkey.pokemonchess.gameengine2d.TouchableObject
import io.github.breadkey.pokemonchess.gameengine2d.Vector3
import io.github.breadkey.pokemonchess.model.gameobject.BattleField

open class PokemonInBattle: TouchableObject(
    touchCollider = BoxCollider(
        offset = Vector3(0f, 0.5f),
        size = Vector3(96f, 96f)
    )
) {
    private lateinit var previousPosition: Vector3

    override fun touched() {
        previousPosition = Vector3(transform.position)
    }

    override fun moved(x: Float, y: Float) {
        val globalTransform = globalTransform
        val realOffset = touchCollider.offset * touchCollider.size * globalTransform.scale

        transform.position.x = x - realOffset.x
        if (parent != null) transform.position.x -= parent!!.globalTransform.position.x
        transform.position.y = y - realOffset.y
        if (parent != null) transform.position.y -= parent!!.globalTransform.position.y
    }

    override fun released(x: Float, y: Float) {
        with (parent as? BattleField) {
            if (this?.place(this@PokemonInBattle) == false) {
                this@PokemonInBattle.transform.position = previousPosition
            }
        }
    }
}