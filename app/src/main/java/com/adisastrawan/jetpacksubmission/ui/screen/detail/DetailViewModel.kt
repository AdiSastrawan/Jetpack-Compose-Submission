package com.adisastrawan.jetpacksubmission.ui.screen.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adisastrawan.jetpacksubmission.data.ChampionRepository
import com.adisastrawan.jetpacksubmission.model.Champion
import com.adisastrawan.jetpacksubmission.ui.common.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: ChampionRepository):ViewModel() {
    private val _uiState : MutableStateFlow<UIState<Champion>> = MutableStateFlow(UIState.Loading)
    val uiState : StateFlow<UIState<Champion>>
        get() = _uiState
    private val _isFavorite : MutableState<Boolean> = mutableStateOf(false)
    val isFavorite : State<Boolean>
        get() = _isFavorite
    fun getChampionByName(name:String){
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            _uiState.value = UIState.Success(repository.getChampionByName(name))
        }
    }
    fun checkFavorite(name:String){
        viewModelScope.launch {
            _isFavorite.value = repository.isFavoriteChampion(name)
        }
    }
    fun addFavorite(champion: Champion){
        viewModelScope.launch {
            repository.addFavoriteChampion(champion)
        }
    }
    fun deleteFavorite(champion: Champion){
        viewModelScope.launch {
            repository.removeFavoriteChampion(champion)
        }
    }
}