package io.github.breadkey.pokemonchess.gameengine2d.sprites

import android.graphics.drawable.BitmapDrawable
import android.support.test.InstrumentationRegistry
import io.github.breadkey.pokemonchess.R
import io.github.breadkey.pokemonchess.gameengine2d.Sprite
import io.github.breadkey.pokemonchess.util.SpriteEditor
import org.junit.Assert.*
import org.junit.Test

class SpriteEditorTest {
    @Test
    fun makeBitmap() {
        val context = InstrumentationRegistry.getTargetContext()
        val bitmapDrawable: BitmapDrawable = context.getDrawable(R.drawable.charmander_front) as BitmapDrawable
        val charmanderBitmap = bitmapDrawable.bitmap

        assertEquals(210, charmanderBitmap.width)
        assertEquals(924, charmanderBitmap.height)
    }

    @Test
    fun charmanderTest() {
        SpriteEditor.context = InstrumentationRegistry.getTargetContext()
        val sprites = SpriteEditor.getSprites(R.drawable.charmander_front, 42, 42, Sprite.Pivot.RIGHT_BOTTOM).subList(0, 107)

        assertEquals(5 * 22 - 3, sprites.size)
    }
}