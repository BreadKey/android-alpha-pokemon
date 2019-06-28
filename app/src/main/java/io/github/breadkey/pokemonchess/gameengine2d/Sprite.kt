package io.github.breadkey.pokemonchess.gameengine2d

import android.graphics.Bitmap

class Sprite {
    enum class Pivot(val x: Float, val y: Float) {
        RIGHT_BOTTOM(0f, 0f), CENTER(0.5f, 0.5f), BOTTOM(0.5f, 0f)
    }

    var  pivot = Pivot.CENTER
    lateinit var bitmap: Bitmap
    var offset = Vector3(0f, 0f, 0f)
}