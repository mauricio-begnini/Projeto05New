package com.example.projeto05new.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projeto05new.data.daos.MatchDao
import com.example.projeto05new.data.daos.PlayerDao
import com.example.projeto05new.data.daos.TeamDao
import com.example.projeto05new.data.models.Match
import com.example.projeto05new.data.models.Player
import com.example.projeto05new.data.models.Team

@Database(
    entities = [Match::class, Team::class, Player::class],
    version = 1,
    exportSchema = false,
)
abstract class TournamentDatabase: RoomDatabase() {

    abstract fun matchDao(): MatchDao
    abstract fun teamDao(): TeamDao
    abstract fun playerDao(): PlayerDao

    companion object {

        @Volatile
        private var INSTANCE: TournamentDatabase? = null

        fun getInstance(context: Context): TournamentDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    TournamentDatabase::class.java,
                    "tournament_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}