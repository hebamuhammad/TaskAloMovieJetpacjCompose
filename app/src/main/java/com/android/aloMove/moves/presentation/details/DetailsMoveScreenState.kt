package com.android.aloMove.moves.presentation.details

import com.android.aloMove.moves.domain.Move

data class DetailsMoveScreenState(
    val move : Move?,
    val isLoading : Boolean,
    val error : String? = null
)