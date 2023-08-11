package com.android.aloMove.moves.model.remote

import com.google.gson.annotations.SerializedName

data class RemoteMove(
    val id : Int ,
    @SerializedName("move_name")
    val title : String ,
    @SerializedName("description")
    val location : String ,
    @SerializedName("is_available")
    val isOpen : Boolean
)