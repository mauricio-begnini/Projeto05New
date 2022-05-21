package com.example.projeto05new

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projeto05new.data.models.Match
import com.example.projeto05new.data.models.Player
import com.example.projeto05new.data.models.Team
import com.example.projeto05new.ui.theme.Projeto05NewTheme
import com.example.projeto05new.views.match.MatchViewModel
import com.example.projeto05new.views.match.MatchViewModelFactory
import com.example.projeto05new.views.player.PlayerViewModel
import com.example.projeto05new.views.player.PlayerViewModelFactory
import com.example.projeto05new.views.team.TeamViewModel
import com.example.projeto05new.views.team.TeamViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val teamViewModel: TeamViewModel by viewModels<TeamViewModel> {
            TeamViewModelFactory(
                (this.applicationContext as TournamentApplication).tournamentDatabase.teamDao()
            )
        }
        val playerViewModel: PlayerViewModel by viewModels<PlayerViewModel> {
            PlayerViewModelFactory(
                (this.applicationContext as TournamentApplication).tournamentDatabase.playerDao()
            )
        }
        val matchViewModel: MatchViewModel by viewModels<MatchViewModel> {
            MatchViewModelFactory(
                (this.applicationContext as TournamentApplication).tournamentDatabase.matchDao()
            )
        }

        setContent {
            Projeto05NewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TournamentApp(
                        matchViewModel,
                        teamViewModel,
                        playerViewModel,
                    )
                }
            }
        }
    }
}

@Composable
fun TournamentApp(
    matchViewModel: MatchViewModel,
    teamViewModel: TeamViewModel,
    playerViewModel: PlayerViewModel,
) {



    /*val players by playerViewModel.allPlayers.observeAsState(listOf())
    val teams by teamViewModel.allTeams.observeAsState(listOf())
    val matches by matchViewModel.allMatches.observeAsState(listOf())*/
    //val teamWithPlaers by teamViewModel.allTeamsWithPlayers.observeAsState(listOf())
    //val playersWithTeams by playerViewModel.allPlayersWithTeams.observeAsState(listOf())
    /*val matchesWithTeamsWithPlayers by matchViewModel.allMatchesWithTeamsWithPlayers.observeAsState(
        listOf())*/

    /*Log.d("Teste","$players")
    Log.d("Teste","$teams")
    Log.d("Teste","$matches")*/
    //Log.d("Teste","$matchesWithTeamsWithPlayers")

}

