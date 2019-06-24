package io.github.breadkey.pokemonchess.gameengine2d.scripts

import io.github.breadkey.pokemonchess.gameengine2d.GameScript
import io.github.breadkey.pokemonchess.gameengine2d.Time

class CharmanderScript: GameScript() {
    override fun update() {
        var step: Float = (-5..5).random() * 0.05f
        gameObject?.transform!!.position.x += Time.delta * step
        step = (-5..5).random() * 0.05f
        gameObject?.transform!!.position.y += Time.delta * step
    }
}