package io.github.breadkey.pokemonchess.util

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import io.github.breadkey.pokemonchess.GlobalApplication
import io.github.breadkey.pokemonchess.gameengine2d.Sprite


@SuppressLint("StaticFieldLeak")
object SpriteEditor {
    var context = GlobalApplication.instance.context()


    fun getSprites(assetName: String, width: Int, height: Int, pixelSize: Int = 1, pivot: Sprite.Pivot? = null): List<Sprite> {
        val sprites = arrayListOf<Sprite>()
        val assetManager = context.assets
        val bitmap = BitmapFactory.decodeStream(assetManager.open(assetName))

        for (y in 0..bitmap.height - height step height) {
            for (x in 0..bitmap.width - width step width) {
                var resultBitmap = Bitmap.createBitmap(bitmap, x, y, width, height)
                if (pixelSize > 1)
                    resultBitmap = Bitmap.createScaledBitmap(resultBitmap, width * pixelSize, height * pixelSize, false)
                val sprite = Sprite()
                sprite.bitmap = resultBitmap
                sprite.pivot = pivot ?: sprite.pivot
                sprites.add(sprite)
            }
        }

        return sprites
    }
}