package io.github.breadkey.pokemonchess.model.gameobject.pokemon

import io.github.breadkey.pokemonchess.R
import io.github.breadkey.pokemonchess.gameengine2d.*
import io.github.breadkey.pokemonchess.gameengine2d.scripts.PokemonScript

class Charmander: PokemonInBattle() {
    init {
        addComponent(SpriteRenderer(
            CharmanderSprites.frontIdle.first()
        ))

        addComponent(AnimationController(R.string.charmander_front_idle).apply {
            addAnim(CharmanderAnims.frontIdle)
        })

        addComponent(PokemonScript())
    }
}