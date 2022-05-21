package com.example.projeto05new.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "player",
    foreignKeys = [
        ForeignKey(
            entity = Team::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("teamId"),
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Player(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val teamId: Int? = null
)
