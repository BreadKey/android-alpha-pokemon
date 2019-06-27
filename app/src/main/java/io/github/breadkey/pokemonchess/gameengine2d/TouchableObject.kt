package io.github.breadkey.pokemonchess.gameengine2d

abstract class TouchableObject: GameObject() {
    var isTouchable = true
    init {
        TouchManager.addTouchableObject(this)
    }

    override fun destroy() {
        TouchManager.removeTouchableObject(this)
        super.destroy()
    }

    fun isTouched(x: Float, y: Float): Boolean {
        val globalTransform = globalTransform
        val width = if (spriteRenderer != null) {
            spriteRenderer!!.sprite.bitmap.width * globalTransform.scale.x / 2
        } else 100f * globalTransform.scale.x

        val height = if (spriteRenderer != null) {
            spriteRenderer!!.sprite.bitmap.height * globalTransform.scale.y / 2
        } else 100f * globalTransform.scale.y

        val touchRect = Rect(globalTransform.position.x, globalTransform.position.y, width, height)

        return touchRect.isInRect(x, y)
    }

    abstract fun touched()
    abstract fun moved(x: Float, y: Float)
    abstract fun released(x: Float, y: Float)
}