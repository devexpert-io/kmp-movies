package io.devexpert.kmpmovies.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilder(): RoomDatabase.Builder<MoviesDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$DATABASE_NAME"
    return Room.databaseBuilder<MoviesDatabase>(
        name = dbFilePath,
        factory =  { MoviesDatabase::class.instantiateImpl() }
    )
}