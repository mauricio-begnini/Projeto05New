package com.example.projeto05new.views.player

import androidx.lifecycle.*
import com.example.projeto05new.data.daos.PlayerDao
import com.example.projeto05new.data.models.Player
import com.example.projeto05new.data.models.PlayerWithTeam
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class PlayerViewModel(private val dao: PlayerDao): ViewModel() {

    val allPlayers: LiveData<List<Player>> = dao.getPlayers().asLiveData()

    val allPlayersWithTeams: LiveData<List<PlayerWithTeam>> = dao.getPlayersWithTeams().asLiveData()

    fun insert(player: Player){
        viewModelScope.launch {
            dao.insert(player)
        }
    }

    fun update(player: Player){
        viewModelScope.launch {
            dao.update(player)
        }
    }

    fun delete(player: Player){
        viewModelScope.launch {
            dao.delete(player)
        }
    }
}

class PlayerViewModelFactory(private val dao: PlayerDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PlayerViewModel::class.java))
            return PlayerViewModel(dao) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}