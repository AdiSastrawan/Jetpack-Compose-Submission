package com.adisastrawan.jetpacksubmission.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adisastrawan.jetpacksubmission.data.ChampionRepository
import com.adisastrawan.jetpacksubmission.model.Champion
import com.adisastrawan.jetpacksubmission.ui.common.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ChampionRepository) : ViewModel() {
    private val _uiState : MutableStateFlow<UIState<List<Champion>>> = MutableStateFlow(UIState.Loading)
    val uiState : StateFlow<UIState<List<Champion>>>  get() = _uiState

    private val _query = mutableStateOf("")
    val query : State<String> get() = _query
    fun getAllChampions(){
        viewModelScope.launch{
            repository.getAllChampions().catch { 
                _uiState.value = UIState.Error(it.message.toString())
            }.collect{
                _uiState.value = UIState.Success(it)
            }
        }
    }

    fun searchChampion(newQuery : String){
        _query.value = newQuery
        _uiState.value = UIState.Loading
        _uiState.value = UIState.Success(repository.searchChampion(query.value))
    }
}