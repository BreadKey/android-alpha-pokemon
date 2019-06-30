package io.github.breadkey.pokemonchess.model.gameobject.pokemon

import io.github.breadkey.pokemonchess.R
import io.github.breadkey.pokemonchess.gameengine2d.AnimationController
import io.github.breadkey.pokemonchess.gameengine2d.CharmanderAnims
import io.github.breadkey.pokemonchess.gameengine2d.CharmanderSprites
import io.github.breadkey.pokemonchess.gameengine2d.SpriteRenderer
import io.github.breadkey.pokemonchess.gameengine2d.scripts.PokemonScript
import io.github.breadkey.pokemonchess.model.data.pokemon.Fire
import io.github.breadkey.pokemonchess.model.data.pokemon.PokemonInChess
import io.github.breadkey.pokemonchess.model.data.pokemon.PokemonSpec
import io.github.breadkey.pokemonchess.model.data.pokemon.Stats

class Charmander: PokemonInChessGameObject(
    pokemonInChess = PokemonInChess(
        pokemonSpec = PokemonSpec(
            id = 6, indexNumber = 6, name = "Charmander", types = listOf(Fire), baseStats = Stats(80)
        )
    )
) {
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