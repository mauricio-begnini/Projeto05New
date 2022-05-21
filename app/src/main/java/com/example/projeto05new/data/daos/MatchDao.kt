package com.example.projeto05new.data.daos

import androidx.room.*
import com.example.projeto05new.data.models.Match
import com.example.projeto05new.data.models.MatchWithTeamWithPlayers
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(match: Match)

    @Update
    suspend fun update(match: Match)

    @Delete
    suspend fun delete(match: Match)

    @Query("SELECT * FROM match")
    fun getMatches(): Flow<List<Match>>

    @Transaction
    @Query("SELECT * FROM match")
    fun getMatchesWithsTeamsWithPlayers(): Flow<List<MatchWithTeamWithPlayers>>

}