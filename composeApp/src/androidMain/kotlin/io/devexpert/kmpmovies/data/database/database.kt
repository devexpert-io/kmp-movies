package io.devexpert.kmpmovies.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<MoviesDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder<MoviesDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}