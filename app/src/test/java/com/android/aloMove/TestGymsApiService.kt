package com.android.aloMove

import com.android.aloMove.moves.model.remote.MoveApiInterface
import com.android.aloMove.moves.model.remote.RemoteMove

class TestMoveApiService : MoveApiInterface {
    override suspend fun getMoves(): List<RemoteMove> {
        return RemoteDummyGymsList.getRemoteDummyData()
    }

    override suspend fun getMove(id: Int): Map<String, RemoteMove> {
        TODO("Not yet implemented")
    }

}