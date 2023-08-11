package com.android.aloMove.moves.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.android.aloMove.moves.presentation.SemanticsDescription
import com.android.aloMove.moves.presentation.movesList.DefaultIcon
import com.android.aloMove.moves.presentation.movesList.MoveDetails

@Composable
fun DetailsMoveScreen(item : DetailsMoveScreenState) {

    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        if (item.isLoading) CircularProgressIndicator(
            modifier = Modifier.semantics { contentDescription = SemanticsDescription.MOVES_LIST_LOADING },
            color = Color.Gray ,
        )
        else
            item.let {
                Column(horizontalAlignment = Alignment.CenterHorizontally , modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    DefaultIcon(
                        icon = Icons.Filled.Place,
                        modifier = Modifier.padding(bottom = 32.dp, top = 32.dp),
                        contentDescription = "Location Icon"
                    )

                    MoveDetails(
                        move = it.move!!,
                        modifier = Modifier.padding(bottom = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    )

                    Text(
                        text = if (it.move.isOpen) "Available" else "Un Available",
                        color = if (it.move.isOpen) Color.Green else Color.Red
                    )
                }
            }
    }

    item.error?.let {
        Text(text = it , modifier = Modifier.padding(start = 16.dp) )
    }

}