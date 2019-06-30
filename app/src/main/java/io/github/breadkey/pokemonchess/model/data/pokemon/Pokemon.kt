package io.github.breadkey.pokemonchess.model.data.pokemon

import io.github.breadkey.pokemonchess.model.data.Trainer

data class PokemonSpec (
    var evolvedFrom: PokemonSpec? = null,
    val rank: Int = 1,
    val cost: Int = 1,
    val id: Int,
    val indexNumber: Int,
    val name: String,
    val types: List<PokemonType>,
    val baseStats: Stats,
    var evolveTo: PokemonSpec? = null
)

class PokemonInChess (
    val pokemonSpec: PokemonSpec,
    var trainer: Trainer? = null
) {
    fun getCost(): Int {
        return if (pokemonSpec.evolvedFrom == null) pokemonSpec.cost
        else {
            3 * pokemonSpec.evolvedFrom!!.cost
        }
    }
}

class Pokemon (
    val id: Int,
    val spec: PokemonSpec,
    var level: Int,
    var nickname: String = spec.name,
    val individualValues: Stats,
    val effortValues: Stats = Stats(0),
    val sex: Boolean?,
    var trainer: Trainer? = null,
    val nature: PokemonNature
) {
    companion object {
        const val MAX_EFFORT_VALUE = 252
        const val MAX_SUM_EFFORT_VALUES = 510
        const val MAX_MOVE_NUM_CAN_LEARN = 4
    }

    val hp: Int get() = commonStatFormula(Stat.HP) + 10 + level
    val attack: Int get() = getStat(Stat.ATTACK)
    val defense: Int get() = getStat(Stat.DEFENSE)
    val specialAttack: Int get() = getStat(Stat.SPECIAL_ATTACK)
    val specialDefense: Int get() = getStat(Stat.SPECIAL_DEFENSE)
    val speed: Int get() = getStat(Stat.SPEED)

    private fun getStat(stat: Stat): Int {
        return nature.revise(stat, commonStatFormula(stat) + 5)
    }

    private fun commonStatFormula(stat: Stat): Int {
        return ((spec.baseStats.get(stat) * 2) + individualValues.get(stat) + effortValues.get(stat) / 4) * level / 100
    }

    fun addEffortValue(stat: Stat, value: Int) {
        var addedValue = value
        val sum = effortValues.sum
        if (sum + addedValue > MAX_SUM_EFFORT_VALUES) {
            addedValue = MAX_SUM_EFFORT_VALUES - sum
        }

        val statValue = effortValues.get(stat)
        if (statValue + addedValue > MAX_EFFORT_VALUE) {
            addedValue = MAX_EFFORT_VALUE - statValue
        }

        effortValues.add(stat, addedValue)
    }
}