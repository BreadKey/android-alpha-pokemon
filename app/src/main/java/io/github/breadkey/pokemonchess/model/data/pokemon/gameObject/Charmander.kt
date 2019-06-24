package io.github.breadkey.pokemonchess.model.data.pokemon.gameObject

import io.github.breadkey.pokemonchess.gameengine2d.*
import io.github.breadkey.pokemonchess.gameengine2d.scripts.CharmanderScript

class Charmander : GameObject() {
    init {
        transform.scale = Vector3(1.5f, 1.5f, 1f)

        addComponent(SpriteRenderer(
            CharmanderSprites.frontIdle.first()
        ))

        addComponent(AnimationController().apply {
            addAnim(CharmanderAnims.frontIdle)
            play(CharmanderAnims.frontIdle.id)
        })

        addComponent(CharmanderScript())
    }
}