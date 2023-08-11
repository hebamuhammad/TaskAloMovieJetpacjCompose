package com.android.aloMove.moves.presentation.details

import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.aloMove.moves.model.DetailsMoveRepository
import com.android.aloMove.moves.model.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class DetailsMoveViewModel @Inject constructor(
    private val  stateHandle: SavedStateHandle,
    private val detailsMoveRepository: DetailsMoveRepository,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel(){

    private var _state by mutableStateOf(
        DetailsMoveScreenState(
            move = null ,
            isLoading = true
        )
    )

    val state: State<DetailsMoveScreenState> get() = derivedStateOf { _state }

    private val errorHandle = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        _state = _state.copy(isLoading = false, error = throwable.message)
    }

    init {

        val moveId = stateHandle.get<Int>("move_id")?:0
        getData(moveId)
    }

    private fun getData(id : Int ){
        viewModelScope.launch(errorHandle + dispatcher ) {
            _state = _state.copy(move = detailsMoveRepository.getMoveData(id) , isLoading = false )

        }
    }

}