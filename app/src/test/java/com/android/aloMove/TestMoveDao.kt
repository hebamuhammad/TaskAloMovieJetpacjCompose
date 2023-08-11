package com.android.aloMove

import com.android.aloMove.moves.model.local.LocalMove
import com.android.aloMove.moves.model.local.LocalMoveFavouriteState
import com.android.aloMove.moves.model.local.MoveDao

class TestMoveDao : MoveDao {
    private val moves = HashMap<Int , LocalMove>()

    override suspend fun getAll(): List<LocalMove> {
        return moves.values.toList()
    }

    override suspend fun addAll(moves: List<LocalMove>) {
        moves.forEach{
            this.moves[it.id] = it
        }
    }

    override suspend fun update(move: LocalMoveFavouriteState) {
        updateGym(move)
    }

    override suspend fun getFavouriteMoves(): List<LocalMove> {
        return moves.values.toList().filter { it.isFavourite }
    }

    override suspend fun updateAll(moves: List<LocalMoveFavouriteState>) {
        moves.forEach {
            updateGym(it)
        }
    }

    private fun updateGym(gym  : LocalMoveFavouriteState){
        val gymObject = this.moves[gym.id]
        gymObject?.let {
            this.moves[gym.id]= gymObject.copy(isFavourite = gym.isFavourite)
        }
    }
}