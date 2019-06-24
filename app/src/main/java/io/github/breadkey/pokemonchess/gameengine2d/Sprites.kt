package io.github.breadkey.pokemonchess.gameengine2d

import io.github.breadkey.pokemonchess.R
import io.github.breadkey.pokemonchess.util.SpriteEditor

open class PokemonSprites (
    val frontIdle: List<Sprite>
)

object CharmanderSprites: PokemonSprites(
    frontIdle = SpriteEditor.getSprites(R.drawable.charmander_front, 42, 42, Sprite.Pivot.LEFT_TOP, 3).subList(0, 107)
)