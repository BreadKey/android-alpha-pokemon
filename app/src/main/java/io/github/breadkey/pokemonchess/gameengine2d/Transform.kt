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
    operator fun plus(increment: Vector3): Vector3 {
        return Vector3(x + increment.x, y + increment.y, z + increment.z)
    }
}