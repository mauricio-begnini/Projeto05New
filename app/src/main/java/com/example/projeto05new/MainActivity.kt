package com.example.projeto05new

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.projeto05new.ui.theme.Projeto05NewTheme
import com.example.projeto05new.views.match.MatchViewModel
import com.example.projeto05new.views.match.MatchViewModelFactory
import com.example.projeto05new.views.match.MatchesScreen
import com.example.projeto05new.views.player.PlayerViewModel
import com.example.projeto05new.views.player.PlayerViewModelFactory
import com.example.projeto05new.views.player.PlayersScreen
import com.example.projeto05new.views.team.TeamViewModel
import com.example.projeto05new.views.team.TeamViewModelFactory
import com.example.projeto05new.views.team.TeamsScreen

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
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomAppBar (
                modifier = Modifier.height(80.dp)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNavScreens.forEach { botNavScreen ->
                    BottomNavigationItem(
                        icon = { Icon(
                            modifier = Modifier.size(50.dp),
                            painter = painterResource(id = botNavScreen.icon), 
                            contentDescription = stringResource(id = botNavScreen.name) 
                        )},
                        label = { Text(text = stringResource(id = botNavScreen.name))},
                        selected = currentDestination?.hierarchy?.any{
                                it.route == botNavScreen.route
                             } == true,
                        onClick = {
                            navController.navigate(botNavScreen.route){
                                popUpTo(navController.graph.startDestinationId){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Screen.MatchScreen.route
        ){
            composable(Screen.MatchScreen.route){
                MatchesScreen()
            }
            composable(Screen.PlayerScreen.route){
                PlayersScreen()
            }
            composable(Screen.TeamScreen.route){
                TeamsScreen()
            }
            composable(Screen.MatchDetails.route){

            }
            composable(Screen.TeamSDetails.route){

            }
            composable(Screen.PlayerDetails.route){

            }
        }
    }
}

private val bottomNavScreens = listOf(
    Screen.MatchScreen,
    Screen.PlayerScreen,
    Screen.TeamScreen
)

sealed class Screen(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val name: Int,
){
    object MatchScreen: Screen("match", R.drawable.match_icon2, R.string.match)
    object PlayerScreen: Screen("player", R.drawable.player_icon, R.string.player)
    object TeamScreen: Screen("team", R.drawable.team_icon, R.string.team)
    object MatchDetails: Screen("match_details", R.drawable.match_icon2, R.string.match)
    object PlayerDetails: Screen("player_details", R.drawable.player_icon, R.string.player)
    object TeamSDetails: Screen("team_details", R.drawable.team_icon, R.string.team)
}



