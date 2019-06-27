package io.github.breadkey.pokemonchess.gameengine2d

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.view.MotionEvent
import android.view.View
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class GameScene(val sceneName: String, context: Context): View(context) {
    init {
        isClickable = true
    }

    val gameObjects = arrayListOf<GameObject>()
    var camera = Transform()

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {

        canvas.save()
        canvas.translate(
            width / 2f + camera.position.x,
            height / 2f - camera.position.y
        )
        canvas.rotate(camera.rotation.z)
        canvas.scale(camera.scale.x, camera.scale.z)

        gameObjects.forEach {
            renderObject(canvas, it)
        }
        super.onDraw(canvas)
        canvas.restore()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            val x = event.x - width / 2 - camera.position.x
            val y = (height / 2 - event.y) - camera.position.y

            when (event.action) {
                MotionEvent.ACTION_DOWN ->
                    TouchManager.touchDown(x, y)
                MotionEvent.ACTION_MOVE ->
                    TouchManager.touchMove(x, y)
                MotionEvent.ACTION_UP -> {
                    TouchManager.touchUp(x, y)
                }
            }
        }

        return super.onTouchEvent(event)
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


    fun pause() {
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
        if (!gameObject.isEnabled) return

        gameObject.getScripts().forEach {
            it.update()
        }

        gameObject.getChildren().forEach { child ->
            updateScript(child)
        }
    }

    private fun renderObject(canvas: Canvas, gameObject: GameObject) {
        if (!gameObject.isEnabled) return

        canvas.save()
        canvas.translate(gameObject.transform.position.x, -gameObject.transform.position.y)
        canvas.rotate(gameObject.transform.rotation.z)
        canvas.scale(gameObject.transform.scale.x, gameObject.transform.scale.y)
        gameObject.spriteRenderer?.render(canvas)

        gameObject.getChildren().forEach { child ->
            renderObject(canvas, child)
        }
        canvas.restore()
    }
}