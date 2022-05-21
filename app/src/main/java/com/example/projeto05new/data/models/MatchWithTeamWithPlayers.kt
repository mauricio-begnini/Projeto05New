package com.example.projeto05new.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class MatchWithTeamWithPlayers(
    @Embedded val match: Match,
    @Relation(
        entity = Team::class,
        parentColumn = "team1Id",
        entityColumn = "id"
    )
    val team1: TeamWithPlayers,
    @Relation(
        entity = Team::class,
        parentColumn = "team2Id",
        entityColumn = "id"
    )
    val team2: TeamWithPlayers,
)
