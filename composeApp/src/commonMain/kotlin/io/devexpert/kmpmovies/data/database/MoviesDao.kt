package io.devexpert.kmpmovies.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.devexpert.kmpmovies.data.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM Movie")
    fun fetchPopularMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun findMovieById(id: Int): Flow<Movie?>

    @Query("SELECT COUNT(id) FROM Movie")
    suspend fun countMovies(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(movies: List<Movie>)

}