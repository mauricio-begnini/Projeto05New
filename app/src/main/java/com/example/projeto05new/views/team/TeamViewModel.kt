package com.example.projeto05new.views.team

import androidx.lifecycle.*
import com.example.projeto05new.data.daos.TeamDao
import com.example.projeto05new.data.models.Team
import com.example.projeto05new.data.models.TeamWithPlayers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TeamViewModel(private val dao: TeamDao): ViewModel() {

    val allTeams: LiveData<List<Team>> = dao.getTeams().asLiveData()

    val allTeamsWithPlayers: LiveData<List<TeamWithPlayers>> = dao.getTeamWithPlayers().asLiveData()

    fun insert(team: Team){
        viewModelScope.launch {
            dao.insert(team)
        }
    }

    fun update(team: Team){
        viewModelScope.launch {
            dao.update(team)
        }
    }

    fun delete(team: Team){
        viewModelScope.launch {
            dao.delete(team)
        }
    }
}

class TeamViewModelFactory(private val dao: TeamDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TeamViewModel::class.java))
            return TeamViewModel(dao) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}