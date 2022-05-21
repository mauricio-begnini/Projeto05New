package com.example.projeto05new.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "match",
    foreignKeys = [
        ForeignKey(
            entity = Team::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("team1Id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Team::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("team2Id"),
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class Match(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val team1Id: Int,
    val team2Id: Int,
    val winner: Int,
)
