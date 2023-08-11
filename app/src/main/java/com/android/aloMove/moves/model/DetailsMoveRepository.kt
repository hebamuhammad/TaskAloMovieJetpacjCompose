package com.android.aloMove.moves.model

import com.android.aloMove.moves.domain.Move
import com.android.aloMove.moves.model.di.IODispatcher
import com.android.aloMove.moves.model.remote.MoveApiInterface
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailsMoveRepository @Inject constructor(
    private var apiService : MoveApiInterface,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    private suspend fun getMoveFromRemoteDB(id : Int) = withContext(dispatcher){apiService.getMove(id)}

    suspend fun getMoveData(id : Int ) : Move {
        return getMoveFromRemoteDB(id).values.first().let {
            Move(it.id, it.title, it.location, it.isOpen)
        }

    }
}