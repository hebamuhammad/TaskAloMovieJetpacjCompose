package com.android.aloMove

import com.android.aloMove.DummyMoveList.getDomainDummyData
import com.android.aloMove.moves.domain.GetInitialMoveUseCase
import com.android.aloMove.moves.domain.GetMovesSortedUseCase
import com.android.aloMove.moves.domain.ToggleFavouriteStateUseCase
import com.android.aloMove.moves.model.MoveRepository
import com.android.aloMove.moves.presentation.movesList.MoveScreenState
import com.android.aloMove.moves.presentation.movesList.MoveViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Test

// معناها ان دول نتحت ال Testing و ممكن يتغيروا ف اى زقت
@ExperimentalCoroutinesApi
class MovesViewModelTest {

    // السطرين اللى بعد دول ع انا مستخدمه coroutines و مش هينفع تظبط هنا فعلشان اظبط ال Scope
    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Test
    fun loadingState_isSetCorrectly() = scope.runTest{
        val viewModel = getViewModel()
        val state = viewModel.state.value
        assert(
            state == MoveScreenState(
                moves = emptyList() ,
                isLoading = true ,
                error = null
            )
        )
    }

    @Test
    fun loadedContentState_isSetCorrectly() = scope.runTest {
        val viewModel = getViewModel()
        // دة بتخلى ال GymsViewModel تشتغل coroutines لحد اما ما يكونش عنده تاسكات فاضله تتنفذ تانى
        advanceUntilIdle()
        val state = viewModel.state.value
        val moves = getDomainDummyData()
        assert(
            state == MoveScreenState(
            moves = moves,
            isLoading = false,
            error = null)
        )
    }

    private fun getViewModel(): MoveViewModel {
        val moveRepository = MoveRepository(TestMoveDao(), TestMoveApiService(), dispatcher)
        val getMovesSortedUseCase = GetMovesSortedUseCase(moveRepository)
        val getInitialMovesUseCase = GetInitialMoveUseCase(moveRepository , getMovesSortedUseCase)
        val toggleFavouriteStateUseCase = ToggleFavouriteStateUseCase(moveRepository , getMovesSortedUseCase)
        return MoveViewModel(getInitialMovesUseCase , toggleFavouriteStateUseCase  , dispatcher)
    }

    // Fakes Data --> Another Classes like TestMoveDao() , TestGymsApiService()
}