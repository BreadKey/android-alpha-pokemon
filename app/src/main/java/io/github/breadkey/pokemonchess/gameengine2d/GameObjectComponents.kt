package io.github.breadkey.pokemonchess.gameengine2d

import android.graphics.Canvas
import android.os.CountDownTimer
import java.lang.reflect.Constructor

open class GameObjectComponent {
    var gameObject: GameObject? = null
}

class SpriteRenderer(var sprite: Sprite): GameObjectComponent() {
    fun render(canvas: Canvas) {
        canvas.save()
        canvas.translate(-sprite.bitmap.width * (1f - sprite.pivot.x), -sprite.bitmap.height * (1f - sprite.pivot.y))
        canvas.drawBitmap(sprite.bitmap, 0f, 0f, null)
        canvas.restore()
    }
}

class AnimationController(val defaultAnimId: Int? = null): GameObjectComponent() {
    private val anims = hashMapOf<Int, Anim>()
    private var delta: Long = 0
    var animationTimer: CountDownTimer? = null

    fun addAnim(anim: Anim) {
        anims[anim.id] = anim
    }

    fun play(id: Int?) {
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
    abstract fun awake()

    fun instantiate(gameObject: GameObject, transform: Transform = Transform(), parent: GameObject? = null) {
        GameSceneManager.currentGameScene?.addGameObject(gameObject)
        gameObject.transform = transform
        parent?.addChild(gameObject)
    }
}

abstract class Collider(val offset: Vector3, val size: Vector3): GameObjectComponent() {
    abstract fun isHit(x: Float, y: Float): Boolean
}

class BoxCollider(offset: Vector3, size: Vector3): Collider(offset, size) {
    override fun isHit(x: Float, y: Float): Boolean {
        val globalTransform = gameObject!!.globalTransform
        val boxSize = size * globalTransform.scale
        val boxCenter = globalTransform.position + boxSize * offset

        val hitBox = Rect(boxCenter.x, boxCenter.y, boxSize.x, boxSize.y)

        return hitBox.isInRect(x, y)
    }
}