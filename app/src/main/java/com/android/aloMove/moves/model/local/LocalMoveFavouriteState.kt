package com.android.aloMove.moves.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class LocalMoveFavouriteState (
    @ColumnInfo(name = "gym_id")
    val id : Int ,
    @ColumnInfo(name = "is_favourite")
    val isFavourite : Boolean = false
)