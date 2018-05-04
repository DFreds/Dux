package com.bugfreebastard.fluxredux.network

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.bugfreebastard.fluxredux.models.MovieObject

@Dao
interface MovieDao {
    @Query("SELECT * from movies")
    fun getAll(): List<MovieObject>

    @Insert
    fun insertAll(vararg movieObject: MovieObject)

    @Insert
    fun insert(movieObject: MovieObject)

    @Delete
    fun delete(movieObject: MovieObject)
}