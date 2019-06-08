package io.github.breadkey.alphapokemon.model.data.pokemon

open class PokemonType(
    val name: String,
    val strengths: List<PokemonType>? = null,
    val weaknesses: List<PokemonType>? = null,
    val immune: List<PokemonType>? = null
)

object Normal: PokemonType(
    name = "Normal",
    weaknesses = listOf(Fight),
    immune = listOf(Ghost)
)

object Fire: PokemonType(
    name = "Fire",
    strengths = listOf(
        Fire,
        Grass,
        Ice,
        Bug,
        Steel,
        Fairy
    ),
    weaknesses = listOf(
        Water,
        Ground,
        Rock
    )
)

object Water: PokemonType(
    name = "Water",
    strengths = listOf(
        Fire,
        Water,
        Ice,
        Steel
    ),
    weaknesses = listOf(
        Grass,
        Electric
    )
)

object Grass: PokemonType(
    name = "Grass",
    strengths = listOf(
        Water,
        Grass,
        Electric,
        Ground
    ),
    weaknesses = listOf(
        Fire,
        Ice,
        Poison,
        Fly,
        Bug
    )
)

object Electric: PokemonType(
    name = "Electric",
    strengths = listOf(
        Electric,
        Fly,
        Steel
    ),
    weaknesses = listOf(Ground)
)

object Ice: PokemonType(
    name = "Ice",
    strengths = listOf(Ice),
    weaknesses = listOf(
        Fire,
        Fight,
        Rock,
        Steel
    )
)

object Fight: PokemonType(
    name = "Fight",
    strengths = listOf(
        Bug,
        Rock,
        Dark
    ),
    weaknesses = listOf(
        Fly,
        Psychic,
        Fairy
    )
)

object Poison: PokemonType(
    name = "Poison",
    strengths = listOf(
        Grass,
        Fight,
        Poison,
        Bug,
        Fairy
    ),
    weaknesses = listOf(
        Ground,
        Psychic
    )
)

object Ground: PokemonType(
    name = "Ground",
    strengths = listOf(
        Poison,
        Rock
    ),
    weaknesses = listOf(
        Water,
        Grass,
        Ice
    ),
    immune = listOf(Electric)
)

object Fly: PokemonType(
    name = "Fly",
    strengths = listOf(
        Grass,
        Fight,
        Bug
    ),
    weaknesses = listOf(
        Electric,
        Ice,
        Rock
    ),
    immune = listOf(Ground)
)

object Psychic: PokemonType(
    name = "Psychic",
    strengths = listOf(
        Fight,
        Psychic
    ),
    weaknesses = listOf(
        Bug,
        Ghost,
        Dark
    )
)

object Bug: PokemonType(
    name = "Bug",
    strengths = listOf(
        Grass,
        Fight,
        Ground
    ),
    weaknesses = listOf(
        Fire,
        Fly,
        Rock
    )
)

object Rock: PokemonType(
    name = "Rock",
    strengths = listOf(
        Normal,
        Fire,
        Poison,
        Fly
    ),
    weaknesses = listOf(
        Water,
        Grass,
        Fight,
        Ground,
        Steel
    )
)

object Ghost: PokemonType(
    name = "Ghost",
    strengths = listOf(
        Poison,
        Bug
    ),
    weaknesses = listOf(
        Ghost,
        Dark
    ),
    immune = listOf(
        Normal,
        Fight
    )
)

object Dragon: PokemonType(
    name = "Dragon",
    strengths = listOf(
        Fire,
        Water,
        Grass,
        Electric
    ),
    weaknesses = listOf(
        Ice,
        Dragon,
        Fairy
    )
)

object Dark: PokemonType(
    name = "Dark",
    strengths = listOf(
        Ghost,
        Dark
    ),
    weaknesses = listOf(
        Fight,
        Bug,
        Fairy
    ),
    immune = listOf(Psychic)
)

object Steel: PokemonType(
    name = "Steel",
    strengths = listOf(
        Normal,
        Grass,
        Ice,
        Fly,
        Psychic,
        Bug,
        Rock,
        Dragon,
        Steel,
        Fairy
    ),
    weaknesses = listOf(
        Fire,
        Fight,
        Ground
    ),
    immune = listOf(Poison)
)

object Fairy: PokemonType(
    name = "Fairy",
    strengths = listOf(
        Fight,
        Bug,
        Dark
    ),
    weaknesses = listOf(
        Poison,
        Steel
    ),
    immune = listOf(Dragon)
)