package com.bugfreebastard.fluxredux.storage

import android.app.Application
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.bugfreebastard.fluxredux.models.MovieObject
import com.bugfreebastard.fluxredux.network.MovieDao

@Database(entities = [(MovieObject::class)], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        fun buildDatabase(application: Application) =
                Room.databaseBuilder(application, MovieDatabase::class.java, "movie-database")
                        .fallbackToDestructiveMigration()
                        .build()
    }
}