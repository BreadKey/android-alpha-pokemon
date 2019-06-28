package io.github.breadkey.pokemonchess.model.gameobject.pokemon

import io.github.breadkey.pokemonchess.R
import io.github.breadkey.pokemonchess.gameengine2d.*
import io.github.breadkey.pokemonchess.gameengine2d.scripts.PokemonScript

class Charizard: PokemonInBattle() {
    init {
        addComponent(SpriteRenderer(CharizardSprites.frontIdle.first()))
        addComponent(PokemonScript())
        addComponent(AnimationController(R.string.charizard_front_idle).apply {
            addAnim(CharizardAnims.frontIdle)
        })
    }
}