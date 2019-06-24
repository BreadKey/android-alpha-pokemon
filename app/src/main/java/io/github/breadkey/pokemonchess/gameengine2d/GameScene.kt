package io.github.breadkey.pokemonchess.gameengine2d

import android.content.Context
import android.graphics.Canvas
import android.os.Handler
import android.view.View
import kotlinx.coroutines.*

open class GameScene(context: Context): View(context) {
    val gameObjects = arrayListOf<GameObject>()
    override fun onDraw(canvas: Canvas) {
        gameObjects.forEach {
            renderObject(canvas, it)
        }
        super.onDraw(canvas)
    }

    private var isPlaying = false
    fun play() {
        isPlaying = true
        GlobalScope.launch {
            while (isPlaying) {
                postInvalidate()
            }
        }
    }

    fun puase() {
        isPlaying = false
    }

    private fun renderObject(canvas: Canvas, gameObject: GameObject) {
        canvas.save()
        canvas.translate(gameObject.transform.position.x, gameObject.transform.position.y)
        gameObject.spriteRenderer?.render(canvas)

        gameObject.getChildren().forEach { child ->
            renderObject(canvas, child)
        }
        canvas.restore()
    }
}