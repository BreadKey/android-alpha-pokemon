package io.github.breadkey.pokemonchess.gameengine2d

data class Transform(
    var scale: Vector3 = Vector3(
        1f,
        1f,
        1f
    ),
    var position: Vector3 = Vector3(),
    var rotation: Vector3 = Vector3()
)

class Vector3(
    var x: Float = 0f, var y:Float = 0f, var z: Float = 0f
) {
    constructor(vector3: Vector3) : this(vector3.x, vector3.y, vector3.z)

    operator fun plus(increment: Vector3): Vector3 {
        return Vector3(x + increment.x, y + increment.y, z + increment.z)
    }

    operator fun times(increment: Vector3): Vector3 {
        return Vector3(x * increment.x, y * increment.y, z * increment.z)
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Vector3) {
            x == other.x && y == other.y && other.z == z
        } else false
    }

    override fun toString(): String {
        return "x: $x, y: $y, z: $z"
    }
}

class Rect(
    var centerX: Float = 0f, var centerY: Float = 0f, var width: Float = 0f, var height: Float = 0f
) {
    fun isInRect(x: Float, y: Float): Boolean {
        val halfWidth = width / 2f
        val halfHeight = height / 2f

        return centerX - halfWidth <= x && x <= centerX + halfWidth && centerY - halfHeight <= y && y <= centerY + halfHeight
    }
}