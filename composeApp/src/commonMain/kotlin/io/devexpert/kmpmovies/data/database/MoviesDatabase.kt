package io.devexpert.kmpmovies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.devexpert.kmpmovies.data.Movie

const val DATABASE_NAME = "movies.db"

interface DB {
    fun clearAllTables() {}
}

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase(), DB {
    abstract fun moviesDao(): MoviesDao

    override fun clearAllTables() { }
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<MoviesDatabase>
): MoviesDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .build()
}