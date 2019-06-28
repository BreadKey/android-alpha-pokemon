package io.github.breadkey.pokemonchess.gameengine2d

import io.github.breadkey.pokemonchess.R

open class PokemonAnims (
    val frontIdle: Anim
)

object CharmanderAnims: PokemonAnims (
    frontIdle = Anim(R.string.charmander_front_idle).apply {
        sprites = CharmanderSprites.frontIdle
        playTime = 107 * 33
    }
)

object CharizardAnims: PokemonAnims (
    frontIdle = Anim(R.string.charizard_front_idle).apply {
        sprites = CharizardSprites.frontIdle
        playTime = 143 * 33
    }
)