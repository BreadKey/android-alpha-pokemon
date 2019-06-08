package io.github.breadkey.alphapokemon.model.data.pokemon

enum class Stat {
    HP, ATTACK, DEFENSE, SPECIAL_ATTACK, SPECIAL_DEFENSE, SPEED
}

class Stats {
    private val stats: MutableMap<Stat, Int> = HashMap()

    constructor(value: Int) {
        Stat.values().forEach { stats[it] = value }
    }

    constructor(hp: Int, attack: Int, defense: Int, specialAttack: Int, specialDefense: Int, speed: Int) {
        stats[Stat.HP] = hp
        stats[Stat.ATTACK] = attack
        stats[Stat.DEFENSE] = defense
        stats[Stat.SPECIAL_ATTACK] = specialAttack
        stats[Stat.SPECIAL_DEFENSE] = specialDefense
        stats[Stat.SPEED] = speed
    }

    val hp: Int
        get() = stats[Stat.HP]!!
    val attack: Int
        get() = stats[Stat.ATTACK]!!
    val defense: Int
        get() = stats[Stat.DEFENSE]!!
    val specialAttack: Int
        get() = stats[Stat.SPECIAL_ATTACK]!!
    val specialDefense: Int
        get() = stats[Stat.SPECIAL_DEFENSE]!!
    val speed: Int
        get() = stats[Stat.SPEED]!!

    val sum: Int
        get() = stats.values.reduce { acc, i -> acc + i }

    fun get(stat: Stat): Int {
        return stats[stat]!!
    }

    fun add(stat: Stat, value: Int) {
        val total = get(stat) + value
        if (total > 0) {
            stats[stat] = total
        } else {
            stats[stat] = 0
        }
    }
}