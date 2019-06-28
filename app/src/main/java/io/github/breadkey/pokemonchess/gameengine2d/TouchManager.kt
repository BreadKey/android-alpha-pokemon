package io.github.breadkey.pokemonchess.gameengine2d

object TouchManager {
    var touchableObjects = arrayListOf<TouchableObject>()
    private var touchedObject: TouchableObject? = null

    fun addTouchableObject(touchableObject: TouchableObject) {
        if (!touchableObjects.contains(touchableObject)) {
            touchableObjects.add(touchableObject)
        }
    }

    fun touchDown(x: Float, y: Float) {
        if (touchedObject != null) return

        touchableObjects.reversed().forEach {
            if (it.isTouchable && it.isEnabled && it.touchCollider.isHit(x, y)) {
                touchedObject = it
                it.touched()
                return
            }
        }
    }

    fun touchMove(x: Float, y: Float) {
        touchedObject?.moved(x, y)
    }

    fun touchUp(x: Float, y: Float) {
        touchedObject?.released(x, y)
        touchedObject = null
    }

    fun removeTouchableObject(touchableObject: TouchableObject) {
        touchableObjects.remove(touchableObject)
    }
}