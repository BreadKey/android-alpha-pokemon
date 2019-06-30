package io.github.breadkey.pokemonchess.model.gameobject.pokemon

import io.github.breadkey.pokemonchess.R
import io.github.breadkey.pokemonchess.gameengine2d.*
import io.github.breadkey.pokemonchess.gameengine2d.scripts.PokemonScript
import io.github.breadkey.pokemonchess.model.data.Trainer
import io.github.breadkey.pokemonchess.model.data.pokemon.*

class Charizard: PokemonInChessGameObject(
    pokemonInChess = PokemonInChess(
        pokemonSpec = PokemonSpec(
            id = 6, indexNumber = 6, name = "Charizard", types = listOf(Fire, Fly), baseStats = Stats(300)
        )
    )
) {
    init {
        addComponent(SpriteRenderer(CharizardSprites.frontIdle.first()))
        addComponent(PokemonScript())
        addComponent(AnimationController(R.string.charizard_front_idle).apply {
            addAnim(CharizardAnims.frontIdle)
        })
    }
}