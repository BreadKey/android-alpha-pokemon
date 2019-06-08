package io.github.breadkey.alphapokemon.model

import io.github.breadkey.alphapokemon.model.data.pokemon.*
import io.github.breadkey.alphapokemon.model.repository.LocalPokemonSpecRepo
import io.github.breadkey.alphapokemon.model.repository.PokemonSpecRepository
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class PokemonTest {
    lateinit var pokemon: Pokemon

    @Before
    fun setUp() {
        val pikachuSpec = LocalPokemonSpecRepo.findByIndexNumber(25)[0]
        pokemon = Pokemon(
            0,
            pikachuSpec,
            level = 50,
            individualValues = Stats(31),
            effortValues = Stats(0),
            sex = true,
            nature = Brave,
            trainer = null
        )
    }

    @Test
    fun setStatsSameValue() {
        val stats = Stats(30)
        assert(stats.hp == 30)
    }

    @Test
    fun sumStats() {
        val stats = Stats(100)
        assert(stats.sum == 600)
    }

    @Test
    fun addEffortValue() {
        pokemon.addEffortValue(Stat.HP, 1000)
        assertEquals(252, pokemon.effortValues.hp)

        pokemon.addEffortValue(Stat.HP, 1000)
        assertEquals(252, pokemon.effortValues.hp)

        pokemon.addEffortValue(Stat.ATTACK, 252)
        pokemon.addEffortValue(Stat.SPEED, 252)

        assertEquals(Pokemon.MAX_SUM_EFFORT_VALUES, pokemon.effortValues.sum)
    }

    @Test
    fun pikachuSample() {
        val pikachu = Pokemon(
            id = 0,
            spec = LocalPokemonSpecRepo.findByIndexNumber(25).first(),
            level = 50,
            individualValues = Stats(30, 31, 30, 31, 31, 31),
            nature = Naive,
            sex = true
        )

        pikachu.addEffortValue(Stat.ATTACK, 22)
        pikachu.addEffortValue(Stat.SPECIAL_ATTACK, 236)
        pikachu.addEffortValue(Stat.SPEED, 252)

        assertEquals(110, pikachu.hp)
        assertEquals(78, pikachu.attack)
        assertEquals(60, pikachu.defense)
        assertEquals(100, pikachu.specialAttack)
        assertEquals(63, pikachu.specialDefense)
        assertEquals(156, pikachu.speed)
    }
}