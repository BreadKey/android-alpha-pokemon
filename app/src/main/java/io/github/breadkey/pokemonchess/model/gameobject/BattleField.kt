package io.github.breadkey.pokemonchess.model.gameobject

import io.github.breadkey.pokemonchess.gameengine2d.*
import io.github.breadkey.pokemonchess.model.PokemonChessGame
import io.github.breadkey.pokemonchess.model.data.Trainer
import io.github.breadkey.pokemonchess.model.gameobject.pokemon.PokemonInChessGameObject

class BattleField(val fieldOwner: Trainer): GameObject() {
    interface fieldEventCallback {

    }

    val fileRankSize = PokemonChessGame.FILE_RANK_SIZE
    val chessBoard = arrayListOf<ArrayList<ChessSquare>>()
    val pokemonsInChessBoardCache = hashMapOf<GameObject, ChessSquare>()
    val waitingTiles = arrayListOf<Tile>()

    init {
        val startPosition = -Tile.Size * 3.5f

        setChessBoard(startPosition)
        setWaitingTiles(startPosition)
    }

    private fun setChessBoard(startPosition: Float) {
        val bottomLeft = startPosition - Tile.Size
        addBottomTiles(bottomLeft, bottomLeft, 6)
        addLeftTiles(bottomLeft)

        val topRight = startPosition + fileRankSize * Tile.Size
        addRightTiles(topRight)
        addTopTiles(topRight, topRight, 2)

        for (rank in 0 until fileRankSize) {
            chessBoard.add(arrayListOf())
            for (file in 0 until fileRankSize) {
                val positionX = startPosition + Tile.Size * file
                val positionY = startPosition + Tile.Size * rank
                val chessTile = ChessSquare(rank, file).apply {
                    transform.position = Vector3(positionX, positionY)
                    if ((file + rank) % 2 == 0)
                        addComponent(SpriteRenderer(TilesetSprites.island[9]))
                    else
                        addComponent(SpriteRenderer(TilesetSprites.island[4]))
                }
                chessBoard[rank].add(chessTile)
                addChild(chessTile)
            }
        }
    }

    private fun setWaitingTiles(startPosition: Float) {
        var startPositionY = startPosition - 2 * Tile.Size

        val right = startPosition + Trainer.MAX_POKEMON_NUM_CAN_WAIT * Tile.Size
        val left = startPosition - Tile.Size

        addTopTiles(startPositionY, right, 14)

        startPositionY -= Tile.Size
        addChild(Tile().apply {
            transform.position = Vector3(left, startPositionY)
            canPlaceObject = false
            addComponent(SpriteRenderer(TilesetSprites.island[15]))
        })
        for (index in 0 until Trainer.MAX_POKEMON_NUM_CAN_WAIT) {
            val startPositionX = startPosition + Tile.Size * index
            val tile = Tile().apply {
                transform.position = Vector3(startPositionX, startPositionY)
                addComponent(SpriteRenderer(TilesetSprites.island[10]))
            }
            addChild(tile)
            waitingTiles.add(tile)
        }
        addChild(Tile().apply {
            transform.position = Vector3(right, startPositionY)
            canPlaceObject = false
            addComponent(SpriteRenderer(TilesetSprites.island[17]))
        })

        addBottomTiles(startPositionY - Tile.Size, startPosition - Tile.Size,18)
    }

    private fun addBottomTiles(bottom: Float, left: Float, bottomLeftTileIndex: Int) {
        val leftBottomEdgeTile = Tile().apply {
            canPlaceObject = false
            transform.position = Vector3(left, bottom)
            addComponent(SpriteRenderer(TilesetSprites.island[bottomLeftTileIndex]))
        }
        addChild(leftBottomEdgeTile)
        for (i in 1..fileRankSize) {
            val positionX = left + Tile.Size * i
            val bottomTile = Tile().apply {
                canPlaceObject = false
                transform.position = Vector3(positionX, bottom)
                addComponent(SpriteRenderer(TilesetSprites.island[bottomLeftTileIndex + 1]))
            }
            addChild(bottomTile)
        }

        val rightBottomEdgeTile = Tile().apply {
            canPlaceObject = false
            transform.position = Vector3(left + (fileRankSize + 1) * Tile.Size, bottom)
            addComponent(SpriteRenderer(TilesetSprites.island[bottomLeftTileIndex + 2]))
        }
        addChild(rightBottomEdgeTile)
    }

    private fun addLeftTiles(bottomLeft: Float) {
        for (i in 1..fileRankSize) {
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
        for (i in 1..fileRankSize) {
           val positionY = topRight - Tile.Size * i
            val tile = Tile().apply {
                canPlaceObject = false
                transform.position = Vector3(topRight, positionY)
                addComponent(SpriteRenderer(TilesetSprites.island[5]))
            }

            addChild(tile)
        }
    }

    private fun addTopTiles(top: Float, right: Float, topRightTileIndex: Int) {
        val topRightTile = Tile().apply {
            canPlaceObject = false
            transform.position = Vector3(right, top)
            addComponent(SpriteRenderer(TilesetSprites.island[topRightTileIndex]))
        }
        addChild(topRightTile)

        for (i in 1..8) {
            val positionX = right - Tile.Size * i
            val tile = Tile().apply {
                canPlaceObject = false
                transform.position = Vector3(positionX, top)
                addComponent(SpriteRenderer(TilesetSprites.island[topRightTileIndex - 1]))
            }

            addChild(tile)
        }

        val topLeftTile = Tile().apply {
            canPlaceObject = false
            transform.position = Vector3(right - Tile.Size * 9, top)
            addComponent(SpriteRenderer(TilesetSprites.island[topRightTileIndex - 2]))
        }
        addChild(topLeftTile)
    }

    fun place(pokemonGameObject: PokemonInChessGameObject, rank: Int, file: Int) {
        val chessSquare = chessBoard[rank][file]
        val previousSquare = pokemonsInChessBoardCache[pokemonGameObject]
        clearSquare(previousSquare)
        if (previousSquare != null) {
            if (chessSquare.placedObject != null) {
                place(chessSquare.placedObject as PokemonInChessGameObject, previousSquare.rank, previousSquare.file)
            }
        }

        clearSquare(pokemonsInChessBoardCache[pokemonGameObject])
        addChild(pokemonGameObject)
        chessSquare.canPlaceObject = false
        chessSquare.placedObject = pokemonGameObject
        pokemonsInChessBoardCache[pokemonGameObject] = chessSquare
        pokemonGameObject.transform.position = Vector3(chessSquare.transform.position)

        fieldOwner.pokemonsInChessBoard[chessSquare.rank][chessSquare.file] = pokemonGameObject.pokemonInChess
    }

    fun place(pokemonGameObject: PokemonInChessGameObject): Boolean {
        val previousParent = pokemonGameObject.parent

        addChild(pokemonGameObject)
        for (file in 0 until chessBoard.size) {
            for (rank in 0 until chessBoard[file].size) {
                val tile = chessBoard[file][rank]
                val tileRect = Rect(tile.transform.position.x, tile.transform.position.y, Tile.Size, Tile.Size)
                if (tileRect.isInRect(pokemonGameObject.transform.position)) {
                    if (isWaitingPokemon(pokemonGameObject)) {
                        val index = fieldOwner.waitingPokemons.indexOf(pokemonGameObject.pokemonInChess)
                        clearWaitingTile(index)

                        if (!tile.canPlaceObject) {
                            placeToWaitingTile(tile.placedObject as PokemonInChessGameObject, index)
                        }
                    }

                    if (!tile.canPlaceObject) {
                        if (pokemonsInChessBoardCache[pokemonGameObject] == null) return false
                    }

                    place(pokemonGameObject, file, rank)
                    return  true
                }
            }
        }

        previousParent?.addChild(pokemonGameObject)
        return false
    }

    private fun isWaitingPokemon(pokemonGameObject: PokemonInChessGameObject): Boolean {
        for (index in 0 until waitingTiles.size) {
            val waitingTile = waitingTiles[index]
            if (waitingTile.placedObject == pokemonGameObject) {
                return true
            }
        }

        return false
    }

    private fun clearSquare(chessSquare: ChessSquare?) {
        if (chessSquare != null) {
            pokemonsInChessBoardCache.remove(chessSquare.placedObject)
            chessSquare.canPlaceObject = true
            chessSquare.placedObject = null
            fieldOwner.pokemonsInChessBoard[chessSquare.rank][chessSquare.file] = null
        }
    }

    fun placeToWaitingTile(pokemonGameObject: PokemonInChessGameObject, index: Int) {
        addChild(pokemonGameObject)

        val tile = waitingTiles[index]

        if (isWaitingPokemon(pokemonGameObject)) {
            val previousIndex = fieldOwner.waitingPokemons.indexOf(pokemonGameObject.pokemonInChess)
            clearWaitingTile(previousIndex)
            if (tile.placedObject != null) {
               placeToWaitingTile(tile.placedObject as PokemonInChessGameObject, previousIndex)
            }
        }

        pokemonGameObject.transform.position = Vector3(tile.transform.position)
        val placedPokemon = tile.placedObject as? PokemonInChessGameObject
        val chessSquare = pokemonsInChessBoardCache[pokemonGameObject]
        clearSquare(chessSquare)

        if (placedPokemon != null) {
            if (chessSquare != null) {
                place(placedPokemon, chessSquare.rank, chessSquare.file)
            }
        }

        tile.placedObject = pokemonGameObject
        fieldOwner.waitingPokemons[index] = pokemonGameObject.pokemonInChess
    }

    fun placeToWaitingTile(pokemonGameObject: PokemonInChessGameObject): Boolean {
        val previousParent = pokemonGameObject.parent

        addChild(pokemonGameObject)
        for (index in 0 until waitingTiles.size) {
            val tile = waitingTiles[index]
            val tileRect = Rect(tile.transform.position.x, tile.transform.position.y, Tile.Size, Tile.Size)

            if (tileRect.isInRect(pokemonGameObject.transform.position)) {
                placeToWaitingTile(pokemonGameObject, index)
                return true
            }
        }

        previousParent?.addChild(pokemonGameObject)
        return false
    }

    private fun clearWaitingTile(index: Int) {
        waitingTiles[index].placedObject = null
        fieldOwner.waitingPokemons[index] = null
    }
}

class ChessSquare(val rank: Int, val file: Int): Tile()

open class Tile: GameObject() {
    companion object {
        const val Size = 32 * 4f
    }

    var placedObject: GameObject? = null
    var canPlaceObject = true
        internal set
}