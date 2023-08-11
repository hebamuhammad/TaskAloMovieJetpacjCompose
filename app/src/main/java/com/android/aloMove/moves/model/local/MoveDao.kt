package com.android.aloMove.moves.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MoveDao {

    @Query("SELECT * FROM Moves")
    suspend fun getAll(): List<LocalMove>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(moves : List<LocalMove>)

    @Update(entity = LocalMove::class)
    suspend fun update(move: LocalMoveFavouriteState)

    @Query("SELECT * FROM Moves where is_favourite = 1")
    suspend fun getFavouriteMoves():List<LocalMove>

    @Update(entity = LocalMove::class)
    suspend fun updateAll(moves : List<LocalMoveFavouriteState>)
}