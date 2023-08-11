package com.android.aloMove.moves.domain

import com.android.aloMove.moves.model.MoveRepository
import javax.inject.Inject


class GetInitialMoveUseCase @Inject constructor(
    private val moveRepository : MoveRepository,
    private val getMovesSortedUseCase : GetMovesSortedUseCase
) {
    suspend operator fun invoke(): List<Move>{
        moveRepository.loadMoves()
        return getMovesSortedUseCase()
    }

}
