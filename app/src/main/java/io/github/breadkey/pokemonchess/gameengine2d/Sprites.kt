package io.github.breadkey.pokemonchess.gameengine2d

import android.content.res.AssetManager
import io.github.breadkey.pokemonchess.R
import io.github.breadkey.pokemonchess.util.SpriteEditor

open class PokemonSprites (
    val frontIdle: List<Sprite>
)

object CharmanderSprites: PokemonSprites(
    frontIdle = SpriteEditor.getSprites("charmander_front.png", 42, 42, 2, Sprite.Pivot.BOTTOM).subList(0, 107)
)

object CharizardSprites: PokemonSprites(
    frontIdle = SpriteEditor.getSprites("charizard_front.png", 89, 91, 2, Sprite.Pivot.BOTTOM).subList(0, 143)
)

object TilesetSprites {
    val island = SpriteEditor.getSprites("island.png", 32, 32, 4)
}