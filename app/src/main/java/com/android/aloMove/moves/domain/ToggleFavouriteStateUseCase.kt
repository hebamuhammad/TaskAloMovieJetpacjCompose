package com.android.aloMove.moves.domain

import com.android.aloMove.moves.model.MoveRepository
import javax.inject.Inject

class ToggleFavouriteStateUseCase @Inject constructor(
    private val moveRepository : MoveRepository,
    private val getMovesSortedUseCase : GetMovesSortedUseCase
){
    suspend operator fun invoke(id : Int , oldState : Boolean) : List<Move>{
        val newState = oldState.not()
        moveRepository.toggleFavouriteMove(id  , newState )
        return getMovesSortedUseCase()
    }
}