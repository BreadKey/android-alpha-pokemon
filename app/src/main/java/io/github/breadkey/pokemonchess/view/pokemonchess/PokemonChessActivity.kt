package io.github.breadkey.pokemonchess.view.pokemonchess

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.github.breadkey.pokemonchess.R
import io.github.breadkey.pokemonchess.gameengine2d.GameSceneManager

class PokemonChessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GameSceneManager.loadScene(getString(R.string.pokemonChessScene), this)
        setContentView(GameSceneManager.currentGameScene)
    }
}
