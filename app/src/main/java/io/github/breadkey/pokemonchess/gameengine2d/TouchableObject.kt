package io.github.breadkey.pokemonchess.gameengine2d

abstract class TouchableObject(val touchCollider: Collider): GameObject() {
    var isTouchable = true
    init {
        TouchManager.addTouchableObject(this)
        addComponent(touchCollider)
    }

    override fun destroy() {
        TouchManager.removeTouchableObject(this)
        super.destroy()
    }

    abstract fun touched()
    abstract fun moved(x: Float, y: Float)
    abstract fun released(x: Float, y: Float)
}