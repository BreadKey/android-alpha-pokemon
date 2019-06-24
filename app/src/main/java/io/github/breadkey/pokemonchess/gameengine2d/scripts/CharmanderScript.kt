package io.github.breadkey.pokemonchess.gameengine2d.scripts

import io.github.breadkey.pokemonchess.gameengine2d.GameScript
import io.github.breadkey.pokemonchess.gameengine2d.Time

class CharmanderScript: GameScript() {
    override fun update() {
        gameObject?.transform!!.rotation.z += Time.delta * 0.2f
    }
}