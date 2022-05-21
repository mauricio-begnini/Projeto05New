package com.example.projeto05new.data.daos

import androidx.room.*
import com.example.projeto05new.data.models.Player
import com.example.projeto05new.data.models.PlayerWithTeam
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(player: Player)

    @Update
    suspend fun update(player: Player)

    @Delete
    suspend fun delete(player: Player)

    @Query("SELECT * FROM player")
    fun getPlayers(): Flow<List<Player>>

    @Query("SELECT * FROM player")
    fun getPlayersWithTeams(): Flow<List<PlayerWithTeam>>

}