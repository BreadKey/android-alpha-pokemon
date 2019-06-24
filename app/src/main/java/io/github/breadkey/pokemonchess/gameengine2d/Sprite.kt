package io.github.breadkey.pokemonchess.gameengine2d

import android.graphics.Bitmap

class Sprite {
    enum class Pivot {
        LEFT_TOP, CENTER
    }

    var  pivot = Pivot.LEFT_TOP
    lateinit var bitmap: Bitmap
    var offset = Vector3(0f, 0f, 0f)
}