package io.github.breadkey.pokemonchess.util

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import io.github.breadkey.pokemonchess.GlobalApplication
import io.github.breadkey.pokemonchess.gameengine2d.Sprite


@SuppressLint("StaticFieldLeak")
object SpriteEditor {
    var context = GlobalApplication.instance.context()


    fun getSprites(resid: Int, width: Int, height: Int, pivot: Sprite.Pivot, pixcelSize: Int = 1): List<Sprite> {
        val sprites = arrayListOf<Sprite>()
        val bitmapDrawable: BitmapDrawable = context.getDrawable(resid) as BitmapDrawable
        val bitmap = bitmapDrawable.bitmap

        for (y in 0..bitmap.height - height step height) {
            for (x in 0..bitmap.width - width step width) {
                var resultBitmap = Bitmap.createBitmap(bitmap, x, y, width, height)
                if (pixcelSize > 1)
                    resultBitmap = Bitmap.createScaledBitmap(resultBitmap, width * pixcelSize, height * pixcelSize, false)
                val sprite = Sprite()
                sprite.bitmap = resultBitmap
                sprite.pivot = pivot
                sprites.add(sprite)
            }
        }

        return sprites
    }
}