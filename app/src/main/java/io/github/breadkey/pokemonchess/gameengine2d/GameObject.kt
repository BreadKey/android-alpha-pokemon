package io.github.breadkey.pokemonchess.gameengine2d

open class GameObject {
    var transform = Transform()
    var parent: GameObject? = null
        set(value) {
            GameSceneManager.currentGameScene?.removeParentGameObject(this)
            field?.removeChild(this)
            field = value
            if (value?.children?.contains(this) == false) {
                value.children.add(this)
            }
        }

    var spriteRenderer: SpriteRenderer? = null
        private set
    private val scripts = arrayListOf<GameScript>()
    private val children = arrayListOf<GameObject>()
    private val components = arrayListOf<GameObjectComponent>()

    var globalTransform: Transform
        get() {
            val position = Vector3(transform.position)
            if (parent != null) {
                val scaledPosition = position * parent!!.globalTransform.scale
                val radian = Math.toRadians(parent!!.globalTransform.rotation.z.toDouble())
                position.x = parent!!.globalTransform.position.x + (scaledPosition.x * Math.cos(radian) + scaledPosition.y * Math.sin(radian)).toFloat()
                position.y = parent!!.globalTransform.position.y + (scaledPosition.y * Math.cos(radian) - scaledPosition.x * Math.sin(radian)).toFloat()
                position.z = parent!!.globalTransform.position.z + position.z
            }

            val scale = transform.scale * if (parent != null) parent!!.globalTransform.scale else Vector3(1f, 1f, 1f)
            val rotation = transform.rotation + if (parent != null) parent!!.globalTransform.rotation else Vector3()

            return Transform().apply {
                this.position = position
                this.scale = scale
                this.rotation = rotation
            }
        }
        set(value) {}

    var isEnabled = true

    @Suppress("Destroy", "Unused")
    open fun destroy() {
        isEnabled = false
        parent?.removeChild(this)
        spriteRenderer = null
        scripts.clear()
        components.clear()
        GameSceneManager.currentGameScene?.removeParentGameObject(this)

        children.forEach {
            destroy()
        }
    }

    fun addChild(child: GameObject) {
        if (!children.contains(child)) {
            child.parent = this
            child.getScripts().forEach {
                it.awake() // 단순 parent 설정과의 차이
            }
        }
    }

    fun removeChild(child: GameObject) {
        children.remove(child)
    }

    fun getChildren(): List<GameObject> {
        return children
    }

    fun addComponent(component: GameObjectComponent) {
        if (!components.contains(component)) {
            when (component) {
                is SpriteRenderer -> spriteRenderer = component
                is GameScript -> scripts.add(component)
            }
            components.add(component)
            component.gameObject = this
        }
    }

    fun removeComponent(component: GameObjectComponent) {
        if (components.contains(component)) {
            when (component) {
                is SpriteRenderer -> spriteRenderer = null
                is GameScript -> scripts.remove(component)
            }
            component.gameObject = null
            components.remove(component)
        }
    }

    fun getScripts(): List<GameScript> {
        return scripts
    }

    fun getComponent(componentClass: Class<out GameObjectComponent>): GameObjectComponent? {
        components.forEach {
            if (it::class.java.name == componentClass.name) {
                return it
            }
        }

        return null
    }
}

