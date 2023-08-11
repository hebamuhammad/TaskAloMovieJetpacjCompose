package com.android.aloMove.moves.model.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MoveApiInterface {

    @GET("moves.json")
    suspend fun getMoves(): List<RemoteMove>

    @GET("moves.json?orderBy=\"id\"" )
    suspend fun getMove(@Query("equalTo") id : Int):Map<String , RemoteMove>
}