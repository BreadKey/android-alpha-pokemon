package io.github.breadkey.pokemonchess.gameengine2d

import android.graphics.Canvas
import android.os.CountDownTimer

open class GameObjectComponent {
    var gameObject: GameObject? = null
}

class SpriteRenderer(var sprite: Sprite): GameObjectComponent() {
    fun render(canvas: Canvas) {
        canvas.save()
        when (sprite.pivot) {
            Sprite.Pivot.CENTER ->
                canvas.translate(-sprite.bitmap.width / 2f, -sprite.bitmap.height / 2f)
        }
        canvas.drawBitmap(sprite.bitmap, 0f, 0f, null)
        canvas.restore()
    }
}

class AnimationController: GameObjectComponent() {
    private val anims = hashMapOf<Int, Anim>()
    private var delta: Long = 0
    var animationTimer: CountDownTimer? = null

    fun addAnim(anim: Anim) {
        anims[anim.id] = anim
    }

    fun play(id: Int) {
        val anim = anims[id]
        if (anim != null) {
            stop()
            delta = anim.playTime / anim.sprites.size
            animationTimer = object : CountDownTimer(anim.playTime, delta) {
                override fun onFinish() {
                    if (anim.isLooping) {
                        start()
                    }
                }

                override fun onTick(millisUntilFinished: Long) {
                    gameObject?.spriteRenderer?.sprite = with((anim.sprites.size - millisUntilFinished / delta).toInt()) {
                        when {
                            this < 0 -> anim.sprites.first()
                            this >= anim.sprites.size -> anim.sprites.last()
                            else -> anim.sprites[this]
                        }
                    }
                }
            }.start()
        }
    }

    fun stop() {
        animationTimer?.cancel()
    }
}

abstract class GameScript: GameObjectComponent() {
    abstract fun update()
}