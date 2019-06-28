package io.github.breadkey.pokemonchess.gameengine2d.scripts

import io.github.breadkey.pokemonchess.R
import io.github.breadkey.pokemonchess.gameengine2d.AnimationController
import io.github.breadkey.pokemonchess.gameengine2d.GameScript
import io.github.breadkey.pokemonchess.gameengine2d.Time

class PokemonScript: GameScript() {
    override fun update() {
        // gameObject?.transform!!.rotation.z += Time.delta * 0.2f
    }

    override fun awake() {
        with ((gameObject?.getComponent(AnimationController::class.java) as? AnimationController)){
            this?.play(defaultAnimId)
        }
    }
}