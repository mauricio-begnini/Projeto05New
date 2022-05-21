package com.example.projeto05new.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class PlayerWithTeam(
    @Embedded val player: Player,
    @Relation(parentColumn = "teamId", entityColumn = "id")
    val team: Team
)
