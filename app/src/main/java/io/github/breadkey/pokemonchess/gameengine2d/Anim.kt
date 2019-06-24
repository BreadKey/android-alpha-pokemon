package io.github.breadkey.pokemonchess.gameengine2d

open class Anim(val id: Int) {
    var playTime: Long = 1000
    var isLooping: Boolean = true
    var sprites = listOf<Sprite>()
}