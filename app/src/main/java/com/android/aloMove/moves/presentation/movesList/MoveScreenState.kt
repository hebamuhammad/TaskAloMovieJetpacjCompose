package com.android.aloMove.moves.presentation.movesList

import com.android.aloMove.moves.domain.Move

data class MoveScreenState(
    val moves : List<Move>,
    val isLoading : Boolean,
    val error : String? = null
)