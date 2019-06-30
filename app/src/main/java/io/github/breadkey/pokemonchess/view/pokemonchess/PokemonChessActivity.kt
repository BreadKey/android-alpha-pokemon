package io.github.breadkey.pokemonchess.view.pokemonchess

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import io.github.breadkey.pokemonchess.R
import io.github.breadkey.pokemonchess.databinding.PokemonChessActivityBinding
import io.github.breadkey.pokemonchess.viewmodel.PokemonChessViewModel
import io.github.breadkey.pokemonchess.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.pokemon_chess_activity.*

class PokemonChessActivity : AppCompatActivity() {
    lateinit var pokemonChessViewModel: PokemonChessViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemonChessViewModel = ViewModelFactory.getViewModel(application, PokemonChessViewModel::class.java) as PokemonChessViewModel
        val dataBinding = DataBindingUtil.setContentView<PokemonChessActivityBinding>(this, R.layout.pokemon_chess_activity)
        dataBinding.viewModel = pokemonChessViewModel
        dataBinding.pokemonChessScene.addView(pokemonChessViewModel.pokemonChessScene)
        dataBinding.pokemonShopPanel.setFadeOnClickListener {
            dataBinding.pokemonShopPanel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        }
        dataBinding.pokemonsForSale.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        dataBinding.pokemonsForSale.adapter = PokemonForSaleAdapter(pokemonChessViewModel)
    }
}
