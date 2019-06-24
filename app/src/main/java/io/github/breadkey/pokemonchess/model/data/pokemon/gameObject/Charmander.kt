package io.github.breadkey.pokemonchess.model.data.pokemon.gameObject

import io.github.breadkey.pokemonchess.R
import io.github.breadkey.pokemonchess.gameengine2d.*
import io.github.breadkey.pokemonchess.util.SpriteEditor

class Charmander : GameObject() {
    init {
        addComponent(SpriteRenderer(
            CharmanderSprites.frontIdle.first()
        ))

        addComponent(AnimationController().apply {
            addAnim(CharmanderAnims.frontIdle)
            play(CharmanderAnims.frontIdle.id)
        })
    }
}