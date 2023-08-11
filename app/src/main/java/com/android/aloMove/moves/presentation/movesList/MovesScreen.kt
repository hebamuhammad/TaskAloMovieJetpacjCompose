package com.android.aloMove.moves.presentation.movesList

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.android.aloMove.moves.domain.Move
import com.android.aloMove.moves.presentation.SemanticsDescription

@Composable
fun MovesScreenUI(state : MoveScreenState, onItemClick : (Int) -> Unit , onFavouriteClick: (id : Int , oldState : Boolean) -> Unit){

    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn(){
            items(state.moves){ move ->
                MoveItem(
                    move = move ,
                    onFavouriteClick = {id , oldState-> onFavouriteClick(id , oldState ) } ,
                    onItemClick = { onItemClick(it) })

            }
        }
        if (state.isLoading) CircularProgressIndicator(
            modifier = Modifier.semantics { contentDescription = SemanticsDescription.MOVES_LIST_LOADING },
            color = Color.Gray )
        state.error?.let {
            Text(text = it , modifier = Modifier.padding(start = 16.dp) )
        }
    }

}

@Composable
fun MoveItem(move : Move, onFavouriteClick : (Int, Boolean) -> Unit, onItemClick : (Int) -> Unit) {
    val icon = if (move.isFavourite) {
        Icons.Filled.Favorite
    }
    else {
        Icons.Filled.FavoriteBorder
    }
    Card(elevation = 4.dp , modifier = Modifier
        .padding(8.dp)
        .clickable { onItemClick(move.id) }) {
        Row(verticalAlignment = Alignment.CenterVertically , modifier = Modifier.padding(8.dp)) {
            DefaultIcon(Icons.Filled.Place , Modifier.weight(.15f) , "Move Icon")
            MoveDetails(move , Modifier.weight(.70f))
            DefaultIcon(icon ,
                Modifier
                    .weight(.15f)
                    .padding(8.dp) , "Favourite icon"
            ) { onFavouriteClick(move.id , move.isFavourite) }
        }
    }
}


@Composable
fun DefaultIcon(icon : ImageVector , modifier: Modifier ,contentDescription : String ,  onClick : () -> Unit = {}){
    Image(
        imageVector = icon,
        contentDescription = contentDescription ,
        modifier = modifier
            .padding(8.dp)
            .clickable {
                onClick()
            } ,
        colorFilter = ColorFilter.tint(
            Color.DarkGray
        )
    )
}
@Composable
fun MoveDetails(move : Move, modifier: Modifier, horizontalAlignment : Alignment.Horizontal = Alignment.Start) {
    Column(modifier = modifier , horizontalAlignment = horizontalAlignment) {
        Text(
            text = move.title,
            style = MaterialTheme.typography.h6,
            color = Color.Black
        )
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium
        ) {
            Text(
                text = move.location,
                style = MaterialTheme.typography.body2 ,
                color = Color.Gray
            )
        }
    }
}
