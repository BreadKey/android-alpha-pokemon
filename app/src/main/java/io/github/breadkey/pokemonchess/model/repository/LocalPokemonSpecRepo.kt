package io.github.breadkey.pokemonchess.model.repository

import io.github.breadkey.pokemonchess.model.data.pokemon.Electric
import io.github.breadkey.pokemonchess.model.data.pokemon.PokemonSpec
import io.github.breadkey.pokemonchess.model.data.pokemon.Stats

object LocalPokemonSpecRepo: PokemonSpecRepository {
    private val pokemonSpecs: MutableList<PokemonSpec> = arrayListOf(
        PokemonSpec(
            id = 0,
            indexNumber = 25,
            name = "Pikachu",
            types = listOf(Electric),
            baseStats = Stats(35, 55, 40, 50, 50, 90)
        )
    )

    override fun addPokemonSpec(pokemonSpec: PokemonSpec) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deletePokemonSpec(pokemonSpec: PokemonSpec) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updatePokemonSpec(pokemonSpec: PokemonSpec) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findByIndexNumber(indexNumber: Int): List<PokemonSpec> {
        val results = arrayListOf<PokemonSpec>()

        pokemonSpecs.forEach {
            if (it.indexNumber == indexNumber) {
                results.add(it)
            }
        }

        return results
    }

    override fun findByName(name: String): List<PokemonSpec> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}