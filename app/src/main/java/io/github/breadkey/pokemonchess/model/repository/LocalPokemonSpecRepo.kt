package io.github.breadkey.pokemonchess.model.repository

import io.github.breadkey.pokemonchess.model.data.pokemon.*

object LocalPokemonSpecRepo: PokemonSpecRepository {
    private val pokemonSpecs = hashMapOf<String, PokemonSpec>(
        "Pikachu" to PokemonSpec(
            rank = 1,
            id = 0,
            indexNumber = 25,
            name = "Pikachu",
            types = listOf(Electric),
            baseStats = Stats(35, 55, 40, 50, 50, 90)
        ),
        "Charmander" to PokemonSpec(
            rank = 1,
            id = 4,
            indexNumber = 4,
            name = "Charmander",
            types = listOf(Fire),
            baseStats = Stats(100)
        ),
        "Charizard" to PokemonSpec(
            rank = 3,
            id = 6,
            indexNumber = 4,
            name = "Charizard",
            types = listOf(Fire, Fly),
            baseStats = Stats(400)
        )
    ).apply {
        this["Charmander"]!!.evolveTo = this["Charizard"]
        this["Charizard"]!!.evolvedFrom = this["Charmander"]
    }

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
            if (it.value.indexNumber == indexNumber) {
                results.add(it.value)
            }
        }

        return results
    }

    override fun findByName(name: String): List<PokemonSpec> {
        return listOf(pokemonSpecs[name]!!)
    }
}