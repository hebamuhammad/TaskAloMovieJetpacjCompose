package com.android.aloMove.moves.model.remote

import com.google.gson.annotations.SerializedName

data class RemoteMove(
    val id : Int ,
    @SerializedName("gym_name")
    val title : String ,
    @SerializedName("gym_location")
    val location : String ,
    @SerializedName("is_open")
    val isOpen : Boolean
)