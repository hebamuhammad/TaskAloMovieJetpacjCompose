package com.android.aloMove.moves.model.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalMove::class ] ,
    version = 5 ,
    exportSchema = false
)
abstract class MoveDatabase : RoomDatabase() {
    abstract val dao : MoveDao
}
