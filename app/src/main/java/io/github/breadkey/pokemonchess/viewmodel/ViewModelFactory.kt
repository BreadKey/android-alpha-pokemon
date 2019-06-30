package io.github.breadkey.pokemonchess.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel

object ViewModelFactory {
    @SuppressLint("StaticFieldLeak")
    private var pokemonChessViewModel: PokemonChessViewModel? = null

    fun getViewModel(application: Application, viewModelClass: Class<out ViewModel>): ViewModel? {
        when (viewModelClass.name) {
            PokemonChessViewModel::class.java.name -> {
                if (pokemonChessViewModel == null) {
                    pokemonChessViewModel = PokemonChessViewModel(application)
                }

                return pokemonChessViewModel
            }
        }

        return null
    }
}