package com.example.projeto05new

import android.app.Application
import com.example.projeto05new.data.TournamentDatabase

class TournamentApplication: Application() {

    val tournamentDatabase: TournamentDatabase by lazy {
        TournamentDatabase.getInstance(this)
    }

}