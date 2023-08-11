package com.android.aloMove.moves.model.di

import android.content.Context
import androidx.room.Room
import com.android.aloMove.moves.model.local.MoveDao
import com.android.aloMove.moves.model.local.MoveDatabase
import com.android.aloMove.moves.model.remote.MoveApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoveDataModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context : Context): MoveDatabase{
        return Room.databaseBuilder(
            context,
            MoveDatabase::class.java,
            "gyms_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideRoomDao(db : MoveDatabase) : MoveDao{
        return db.dao
    }

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://alomovie-default-rtdb.firebaseio.com/")
            .build()
    }

    @Provides
    fun provideMoveApiInterface (retrofit: Retrofit) : MoveApiInterface{
        return retrofit.create(MoveApiInterface::class.java)
    }
}