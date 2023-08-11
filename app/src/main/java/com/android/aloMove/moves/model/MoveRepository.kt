package com.android.aloMove.moves.model

import com.android.aloMove.moves.domain.Move
import com.android.aloMove.moves.model.di.IODispatcher
import com.android.aloMove.moves.model.local.MoveDao
import com.android.aloMove.moves.model.local.LocalMove
import com.android.aloMove.moves.model.local.LocalMoveFavouriteState
import com.android.aloMove.moves.model.remote.MoveApiInterface
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoveRepository @Inject constructor(
    private var moveDao : MoveDao,
    private var apiService : MoveApiInterface,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun toggleFavouriteMove(gymId : Int, state : Boolean)= withContext(dispatcher){
        moveDao.update(
            LocalMoveFavouriteState(
                id = gymId ,
                isFavourite = state
            )
        )
    }

     suspend fun loadMoves() = withContext(dispatcher) {
        try {
            updateLocalDatabase()
        } catch (ex: Exception) {
            if (moveDao.getAll().isEmpty()){
                throw Exception("Something went wrong, No data was found, try connecting to internet.")
            }
        }
    }

    suspend fun getMoves(): List<Move>{
        return withContext(dispatcher){
            return@withContext moveDao.getAll().map {
                Move(it.id , it.title , it.location , it.isOpen , it.isFavourite)
            }
        }
    }

    private suspend fun updateLocalDatabase(){
        val moves = apiService.getMoves()

        val favouriteMoveList = moveDao.getFavouriteMoves()

        moveDao.addAll(moves.map {
            LocalMove(it.id , it.title , it.location , it.isOpen )
        })

        moveDao.updateAll(
            favouriteMoveList.map { gym ->
                LocalMoveFavouriteState(gym.id , true)
            }
        )
    }
}