package com.android.aloMove.moves.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.android.aloMove.moves.presentation.details.DetailsMoveScreen
import com.android.aloMove.moves.presentation.details.DetailsMoveViewModel
import com.android.aloMove.moves.presentation.movesList.MoveViewModel
import com.android.aloMove.moves.presentation.movesList.MovesScreenUI
import com.android.aloMove.ui.theme.JetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme{
                MovesAroundApp()
            }
        }
    }
}

@Composable
private fun MovesAroundApp(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "moves"){
        composable(route = "moves"){
            val vm : MoveViewModel = hiltViewModel()
            MovesScreenUI(
                state = vm.state.value ,
                onItemClick = { id->
                    navController.navigate("move/$id")
                },
                onFavouriteClick = { id , oldState ->
                    vm.toggleFavouriteIcon(id , oldState)

                }
            )
        }
       composable(
            route = "move/{move_id}" ,
            arguments = listOf(
                navArgument("move_id"){
                    type = NavType.IntType
                },
            ),
        ){
           val viewModel : DetailsMoveViewModel = hiltViewModel()
           DetailsMoveScreen(item = viewModel.state.value)
        }
    }
}