package com.android.aloMove

import com.example.jetpackcompose.RemoteDummyGymsList.getDomainDummyData
import com.example.jetpackcompose.gyms.domain.GetGymsSortedUseCase
import com.example.jetpackcompose.gyms.domain.ToggleFavouriteStateUseCase
import com.example.jetpackcompose.gyms.model.GymRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class ToggleFavouriteStateUseCaseTest {

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Test
    fun toggleFavouriteState_updateFavouriteProperty() = scope.runTest {
        // Setup
        val gymRepository : GymRepository = GymRepository(TestMoveDao(), TestGymsApiService(), dispatcher)
        val getGymsSortedUseCase : GetGymsSortedUseCase = GetGymsSortedUseCase(gymRepository)
        val useCaseUnderTest = ToggleFavouriteStateUseCase(gymRepository ,getGymsSortedUseCase )

        gymRepository.loadGyms()
        advanceUntilIdle()

        val gyms = getDomainDummyData()
        val gymUnderTest = gyms[0]
        val isFav = gymUnderTest.isFavourite

        val updatedGymList = useCaseUnderTest(gymUnderTest.id , isFav)
        advanceUntilIdle()

        assert(updatedGymList[0].isFavourite == !isFav)
    }

}