package io.github.breadkey.pokemonchess.view.pokemonchess

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class PokemonChessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(PokemonChessScene(this))
    }
}
