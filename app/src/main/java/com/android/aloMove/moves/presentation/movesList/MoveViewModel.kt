package com.android.aloMove.moves.presentation.movesList

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.aloMove.moves.domain.GetInitialMoveUseCase
import com.android.aloMove.moves.domain.Move
import com.android.aloMove.moves.domain.ToggleFavouriteStateUseCase
import com.android.aloMove.moves.model.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class MoveViewModel @Inject constructor(
    private val getAllMovesUseCase : GetInitialMoveUseCase,
    private val toggleFavouriteStateUseCase : ToggleFavouriteStateUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher) : ViewModel() {

    private var _state by mutableStateOf(
        MoveScreenState(
            moves = emptyList<Move>(),
            isLoading = true
        )
    )

    val state: State<MoveScreenState> get() = derivedStateOf { _state }

    private val errorHandle = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        _state = _state.copy(isLoading = false, error = throwable.message)
    }

    init {
        getMoves()
    }

    private fun getMoves() {
        viewModelScope.launch(errorHandle + dispatcher) {
            val receivedMoves = getAllMovesUseCase()
            _state = _state.copy(
                moves = receivedMoves,
                isLoading = false
            )
        }
    }

    fun toggleFavouriteIcon(gymId: Int, oldState: Boolean) {

        viewModelScope.launch(errorHandle + dispatcher) {
            val updatedMovesList = toggleFavouriteStateUseCase(gymId, oldState)
            _state = _state.copy(
                moves = updatedMovesList,
            )
        }
    }
}