package io.github.breadkey.pokemonchess.gameengine2d

import org.junit.Test
import org.junit.Assert.*

class GameObjectTest {
    val grandParent = GameObject().apply {
        transform.position.x = 30f
        transform.position.y = 30f
        transform.scale.x = 1.5f
        transform.scale.y = 1.5f
        transform.rotation.z = 45f
    }

    @Test
    fun globalTransformOfGrandParent() {
        assertEquals(Vector3(30f, 30f), grandParent.globalTransform.position)
        assertEquals(Vector3(1.5f, 1.5f, 1f), grandParent.globalTransform.scale)
        assertEquals(Vector3(0f, 0f, 45f), grandParent.globalTransform.rotation)
    }

    @Test
    fun globalTransformOfParent() {
        val parent = GameObject().apply {
            transform = grandParent.transform
        }
        grandParent.addChild(parent)

        assertEquals(Vector3(30f + 45f * Math.sqrt(2.0).toFloat(), 30f), parent.globalTransform.position)
        assertEquals(Vector3(1.5f * 1.5f, 1.5f * 1.5f, 1.0f), parent.globalTransform.scale)
    }
}

