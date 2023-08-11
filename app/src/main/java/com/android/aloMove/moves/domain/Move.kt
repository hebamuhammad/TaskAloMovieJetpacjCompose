package com.android.aloMove.moves.domain


data class Move(
    val id : Int ,
    val title : String ,
    val location : String ,
    val isOpen : Boolean ,
    val isFavourite : Boolean = false
)