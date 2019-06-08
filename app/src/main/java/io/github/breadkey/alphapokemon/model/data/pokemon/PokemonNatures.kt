package io.github.breadkey.alphapokemon.model.data.pokemon

open class PokemonNature (
    val name: String,
    val upStat: Stat? = null,
    val downStat: Stat? = null
) {
    companion object {
        const val UP_STAT_RATIO = 1.1
        const val DOWN_STAT_RATIO = 0.9
    }

    fun revise(stat: Stat, value: Int): Int {
        return when (stat) {
            upStat -> (value * UP_STAT_RATIO).toInt()
            downStat -> (value * DOWN_STAT_RATIO).toInt()
            else -> value
        }
    }
}

object Lonely: PokemonNature (
    name = "Lonely",
    upStat = Stat.ATTACK,
    downStat = Stat.DEFENSE
)

object Adamant: PokemonNature (
    name = "Adamant",
    upStat = Stat.ATTACK,
    downStat = Stat.SPECIAL_ATTACK
)

object Naughty: PokemonNature (
    name = "Naughty",
    upStat = Stat.ATTACK,
    downStat = Stat.SPECIAL_DEFENSE
)

object Brave: PokemonNature (
    name = "Brave",
    upStat = Stat.ATTACK,
    downStat = Stat.SPEED
)

object Bold: PokemonNature (
    name = "Bold",
    upStat = Stat.DEFENSE,
    downStat = Stat.ATTACK
)

object Impish: PokemonNature (
    name = "Impish",
    upStat = Stat.DEFENSE,
    downStat = Stat.SPECIAL_ATTACK
)

object Lax: PokemonNature (
    name = "Lax",
    upStat = Stat.DEFENSE,
    downStat = Stat.SPECIAL_DEFENSE
)

object Relaxed: PokemonNature (
    name = "Relaxed",
    upStat = Stat.DEFENSE,
    downStat = Stat.SPEED
)

object Naive: PokemonNature (
    name = "Naive",
    upStat = Stat.SPEED,
    downStat = Stat.SPECIAL_DEFENSE
)