package com.android.aloMove

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.android.aloMove.DummyGymsList.getDummyData
import com.example.jetpackcompose.gyms.presentation.SemanticsDescription
import com.example.jetpackcompose.gyms.presentation.gymsList.GymScreenState
import com.example.jetpackcompose.gyms.presentation.gymsList.GymScreenUI
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import org.junit.Rule
import org.junit.Test

// Files اللى بتبقي ف ال Folder دة بتكون محتاجه Android Device اذا كان Emulator او اى جهاز تانى .
class GymsScreenTest {
    // ال rule دى عبارة عن عصايه سحريه بيكون فيها tools بتساعدنى و انا بتيست مثلا ع activity او compose هو ال already مش موجود و مش هيكريت activity او compose هو اللى بيعمله كانه بيخلى موجود .
    // و هتساعدنى بردوا انى set content او ال datat اللى عاوزها ع اقدر اعمل testing بيها
    @get:Rule
    val testRule : ComposeContentTestRule = createComposeRule()

    @Test
    fun loadingState_isActive(){
        // كانى كدة عاملت شااشه فاضيه باستخدام العصايه السحريه بتاعت ل test rule
        // و خليت ال Loading يبان بس لازم دلوقتى استخدم حاجه تخلينى اشوف او اتاكد ان ال Loading باين و الا لا
        testRule.setContent {
            JetpackComposeTheme{
                GymScreenUI(state = GymScreenState(
                  gyms = emptyList(),
                  isLoading = true
                ), onItemClick = {}, onFavouriteClick = {_:Int , _:Boolean -> })
            }
        }

        // كدة بشوف ال Semantiv description اللى هو عبارة عن الconetent description ظاهر و الا لا
        testRule.onNodeWithContentDescription(SemanticsDescription.GYMS_LIST_LOADING).assertIsDisplayed()
    }

    @Test
    fun loadedContentState_isActive(){
        val gymsList = getDummyData()
        testRule.setContent {
            JetpackComposeTheme{
                GymScreenUI(state = GymScreenState(
                    gyms = gymsList,
                    isLoading = false
                ), onItemClick = {}, onFavouriteClick = {_:Int , _:Boolean -> })
            }
        }

        testRule.onNodeWithContentDescription(SemanticsDescription.GYMS_LIST_LOADING).assertDoesNotExist()
        testRule.onNodeWithText(gymsList[0].title).assertIsDisplayed()
    }

    @Test
    fun errorState_isActive(){
        var errorMessage = "Error Found"
        testRule.setContent {
            JetpackComposeTheme{
                GymScreenUI(state = GymScreenState(
                    gyms = emptyList(),
                    isLoading = false ,
                    errorMessage
                ), onItemClick = {}, onFavouriteClick = {_:Int , _:Boolean -> })
            }
        }

        testRule.onNodeWithContentDescription(SemanticsDescription.GYMS_LIST_LOADING).assertDoesNotExist()
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun onItemClicked_idIsPassedCorrectly(){
        val gymsList = getDummyData()
        val gymItem = gymsList[0]
        testRule.setContent {
            JetpackComposeTheme{
                GymScreenUI(state = GymScreenState(
                    gyms = gymsList,
                    isLoading = false
                ), onItemClick = {id ->
                    // علشان اتاكد ان ال Id فعلا بيت Pass صح
                    assert(id == gymItem.id)
                }, onFavouriteClick = {_:Int , _:Boolean -> })
            }
        }
        //علشان اقدر اعمل Click
        testRule.onNodeWithText(gymItem.title).performClick()
    }

    // كل اللى كانوا فوق دة كان Testing ع ال Compose UI
    // كان متطبق بال Unit testing
}