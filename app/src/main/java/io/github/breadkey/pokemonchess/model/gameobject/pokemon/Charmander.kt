package io.github.breadkey.pokemonchess.model.gameobject.pokemon

import io.github.breadkey.pokemonchess.gameengine2d.*
import io.github.breadkey.pokemonchess.gameengine2d.scripts.CharmanderScript
import kotlin.coroutines.coroutineContext

class Charmander : TouchableObject() {
    init {
        addComponent(SpriteRenderer(
            CharmanderSprites.frontIdle.first()
        ))

        addComponent(AnimationController().apply {
            addAnim(CharmanderAnims.frontIdle)
            play(CharmanderAnims.frontIdle.id)
        })

        addComponent(CharmanderScript())
    }

    override fun touched() {

    }

    override fun moved(x: Float, y: Float) {
        transform.position.x = x
        if (parent != null) transform.position.x -= parent!!.globalTransform.position.x
        transform.position.y = y
        if (parent != null) transform.position.y -= parent!!.globalTransform.position.y
    }

    override fun released(x: Float, y: Float) {

    }
}