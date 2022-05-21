package com.example.projeto05new.views.match

import androidx.lifecycle.*
import com.example.projeto05new.data.daos.MatchDao
import com.example.projeto05new.data.models.Match
import com.example.projeto05new.data.models.MatchWithTeamWithPlayers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MatchViewModel(private val dao: MatchDao): ViewModel() {

    val allMatches: LiveData<List<Match>> = dao.getMatches().asLiveData()

    val allMatchesWithTeamsWithPlayers: LiveData<List<MatchWithTeamWithPlayers>>
        = dao.getMatchesWithsTeamsWithPlayers().asLiveData()

    fun insert(match: Match){
        viewModelScope.launch {
            dao.insert(match)
        }
    }

    fun update(match: Match){
        viewModelScope.launch {
            dao.update(match)
        }
    }

    fun delete(match: Match){
        viewModelScope.launch {
            dao.delete(match)
        }
    }
}

class MatchViewModelFactory(private val dao: MatchDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MatchViewModel::class.java))
            return MatchViewModel(dao) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}