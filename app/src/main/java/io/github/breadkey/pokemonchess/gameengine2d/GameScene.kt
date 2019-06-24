package io.github.breadkey.pokemonchess.gameengine2d

import android.content.Context
import android.graphics.Canvas
import android.view.View
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

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
        Time.delta = 0L
        GlobalScope.launch {
            while (isPlaying) {
                update()
            }
        }
    }


    fun puase() {
        isPlaying = false
    }

    private fun update() {
        val startTime = System.currentTimeMillis()
        gameObjects.forEach { gameObject ->
            updateScript(gameObject)
        }
        postInvalidate()
        Time.delta = System.currentTimeMillis() - startTime
    }

    private fun updateScript(gameObject: GameObject) {
        gameObject.getScripts().forEach {
            it.update()
        }

        gameObject.getChildren().forEach { child ->
            updateScript(child)
        }
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