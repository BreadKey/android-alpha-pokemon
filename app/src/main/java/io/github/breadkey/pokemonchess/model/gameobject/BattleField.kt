package io.github.breadkey.pokemonchess.model.gameobject

import io.github.breadkey.pokemonchess.gameengine2d.*
import kotlinx.coroutines.channels.produce

class BattleField: GameObject() {
    val tiles = arrayListOf<ArrayList<Tile>>()
    val placedObjectsCache = hashMapOf<GameObject, Tile>()

    init {
        val startPosition = -Tile.Size * 3.5f

        val bottomLeft = startPosition - Tile.Size
        addBottomTiles(bottomLeft)
        addLeftTiles(bottomLeft)

        val topRight = startPosition + 8 * Tile.Size
        addRightTiles(topRight)
        addTopTiles(topRight)

        for (file in 0..7) {
            tiles.add(arrayListOf())
            for (rank in 0..7) {
                val positionX = startPosition + Tile.Size * rank
                val positionY = startPosition + Tile.Size * file
                val tile = Tile().apply {
                    transform.position = Vector3(positionX, positionY)
                    if ((file + rank) % 2 == 0)
                        addComponent(SpriteRenderer(TilesetSprites.island[9]))
                    else
                        addComponent(SpriteRenderer(TilesetSprites.island[4]))
                }
                tiles[file].add(tile)
                addChild(tile)
            }
        }
    }

    private fun addBottomTiles(bottomLeft: Float) {
        val leftBottomEdgeTile = Tile().apply {
            canPlaceObject = false
            transform.position = Vector3(bottomLeft, bottomLeft)
            addComponent(SpriteRenderer(TilesetSprites.island[6]))
        }
        addChild(leftBottomEdgeTile)
        for (i in 1..8) {
            val positionX = bottomLeft + Tile.Size * i
            val bottomTile = Tile().apply {
                canPlaceObject = false
                transform.position = Vector3(positionX, bottomLeft)
                addComponent(SpriteRenderer(TilesetSprites.island[7]))
            }
            addChild(bottomTile)
        }

        val rightBottomEdgeTile = Tile().apply {
            canPlaceObject = false
            transform.position = Vector3(bottomLeft + 9 * Tile.Size, bottomLeft)
            addComponent(SpriteRenderer(TilesetSprites.island[8]))
        }
        addChild(rightBottomEdgeTile)
    }

    private fun addLeftTiles(bottomLeft: Float) {
        for (i in 1..8) {
            val positionY = bottomLeft + Tile.Size * i
            val tile = Tile().apply {
                canPlaceObject = false
                transform.position = Vector3(bottomLeft, positionY)
                addComponent(SpriteRenderer(TilesetSprites.island[3]))
            }

            addChild(tile)
        }
    }

    private fun addRightTiles(topRight: Float) {
        for (i in 1..8) {
           val positionY = topRight - Tile.Size * i
            val tile = Tile().apply {
                canPlaceObject = false
                transform.position = Vector3(topRight, positionY)
                addComponent(SpriteRenderer(TilesetSprites.island[5]))
            }

            addChild(tile)
        }
    }

    private fun addTopTiles(topRight: Float) {
        val topRightTile = Tile().apply {
            canPlaceObject = false
            transform.position = Vector3(topRight, topRight)
            addComponent(SpriteRenderer(TilesetSprites.island[2]))
        }
        addChild(topRightTile)

        for (i in 1..8) {
            val positionX = topRight - Tile.Size * i
            val tile = Tile().apply {
                canPlaceObject = false
                transform.position = Vector3(positionX, topRight)
                addComponent(SpriteRenderer(TilesetSprites.island[1]))
            }

            addChild(tile)
        }

        val topLeftTile = Tile().apply {
            canPlaceObject = false
            transform.position = Vector3(topRight - Tile.Size * 9, topRight)
            addComponent(SpriteRenderer(TilesetSprites.island[0]))
        }
        addChild(topLeftTile)
    }

    fun place(gameObject: GameObject, file: Int, rank: Int) {
        val tile = tiles[file][rank]

        if (tile.canPlaceObject) {
            clearTile(placedObjectsCache[gameObject])

            addChild(gameObject)
            tile.canPlaceObject = false
            tile.placedObject = gameObject
            placedObjectsCache[gameObject] = tile
            gameObject.transform.position = Vector3(tile.transform.position)
        }
    }

    fun place(gameObject: GameObject): Boolean {
        val previousParent = gameObject.parent

        addChild(gameObject)
        for (file in 0 until tiles.size) {
            for (rank in 0 until tiles[file].size) {
                val tile = tiles[file][rank]
                if (!tile.canPlaceObject) continue

                val tileRect = Rect(tile.transform.position.x, tile.transform.position.y, Tile.Size, Tile.Size)
                if (tileRect.isInRect(gameObject.transform.position)) {
                    place(gameObject, file, rank)
                    return  true
                }
            }
        }

        previousParent?.addChild(gameObject)
        return false
    }

    private fun clearTile(tile: Tile?) {
        tile?.canPlaceObject = true
        tile?.placedObject = null
    }
}

class Tile: GameObject() {
    companion object {
        const val Size = 32 * 4f
    }

    var placedObject: GameObject? = null
    var canPlaceObject = true
        internal set
}