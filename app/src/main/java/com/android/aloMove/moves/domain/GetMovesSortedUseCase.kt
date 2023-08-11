package com.android.aloMove.moves.domain

import com.android.aloMove.moves.model.MoveRepository
import javax.inject.Inject

class GetMovesSortedUseCase @Inject constructor(
    private val moveRepository : MoveRepository) {
    suspend operator fun invoke(): List<Move>{
        return moveRepository.getMoves().sortedBy { it.title }
    }
}