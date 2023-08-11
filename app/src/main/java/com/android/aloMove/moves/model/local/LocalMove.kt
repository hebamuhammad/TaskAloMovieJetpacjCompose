package com.android.aloMove.moves.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Moves")
data class LocalMove(
    @PrimaryKey
    @ColumnInfo(name = "gym_id")
    val id : Int ,
    @ColumnInfo(name = "gym_name")
    val title : String ,
    @ColumnInfo(name = "gym_location")
    val location : String ,
    val isOpen : Boolean ,
    @ColumnInfo(name = "is_favourite")
    val isFavourite : Boolean = false
)