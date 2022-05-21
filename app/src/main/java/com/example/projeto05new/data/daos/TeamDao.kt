package com.example.projeto05new.data.daos

import androidx.room.*
import com.example.projeto05new.data.models.Team
import com.example.projeto05new.data.models.TeamWithPlayers
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(team: Team)

    @Update
    suspend fun update(team: Team)

    @Delete
    suspend fun delete(team: Team)

    @Query("SELECT * FROM team")
    fun getTeams(): Flow<List<Team>>

    @Transaction
    @Query("SELECT * FROM team")
    fun getTeamWithPlayers(): Flow<List<TeamWithPlayers>>

}