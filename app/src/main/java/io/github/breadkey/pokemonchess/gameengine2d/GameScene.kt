package io.github.breadkey.pokemonchess.gameengine2d

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.view.MotionEvent
import android.view.View
import io.github.breadkey.pokemonchess.model.gameobject.Tile
import kotlinx.coroutines.*

abstract class GameScene(val sceneName: String, context: Context): View(context) {
    init {
        isClickable = true
    }

    private val parentGameObjects = arrayListOf<GameObject>()
    var camera = Transform()

    val gameUpdateContext = newSingleThreadContext("GameUpdateContext")

    var ratio: Float? = null



    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        if (ratio == null) ratio = (width / 10) / Tile.Size
        canvas.save()
        canvas.translate(
            width / 2f + camera.position.x,
            height / 2f - camera.position.y
        )
        canvas.rotate(camera.rotation.z)
        canvas.scale(ratio!!, ratio!!)
        canvas.scale(camera.scale.x, camera.scale.y)

        for (gameObject in parentGameObjects) {
            renderObject(canvas, gameObject)
        }
        super.onDraw(canvas)
        canvas.restore()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            val x = (event.x - width / 2) / ratio!! / camera.scale.x - camera.position.x
            val y = (height / 2 - event.y) / ratio!! / camera.scale.y - camera.position.y

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

    var isPlaying = false

    fun play() = runBlocking {
        isPlaying = true
        Time.delta = 0L
        GlobalScope.launch {
            while (isPlaying) {
                withContext(gameUpdateContext) {
                    update()
                }

                GlobalScope.launch(Dispatchers.Main) {
                    invalidate()
                }
            }
        }
    }


    fun pause() {
        isPlaying = false
    }

    private suspend fun update() {
        val startTime = System.currentTimeMillis()
        parentGameObjects.forEach { gameObject ->
            updateScript(gameObject)
        }
        Time.delta = System.currentTimeMillis() - startTime
    }

    private suspend fun updateScript(gameObject: GameObject) {
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

    fun addParentGameObject(gameObject: GameObject) {
        if (!parentGameObjects.contains(gameObject)) {
            parentGameObjects.add(gameObject)
            gameObject.getScripts().forEach {
                it.awake()
            }
        }
    }

    fun removeParentGameObject(gameObject: GameObject) {
        parentGameObjects.remove(gameObject)
    }

    abstract fun initializeScene()

    companion object {
        val IS_PLAYING = 0
    }
}