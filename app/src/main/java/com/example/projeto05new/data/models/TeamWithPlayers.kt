package com.example.projeto05new.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class TeamWithPlayers(
    @Embedded val team: Team,
    @Relation(
        parentColumn = "id",
        entityColumn = "teamId"
    )
    val players: List<Player>
)
