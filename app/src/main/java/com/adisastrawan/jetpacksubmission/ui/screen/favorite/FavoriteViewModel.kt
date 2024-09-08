package com.adisastrawan.jetpacksubmission.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adisastrawan.jetpacksubmission.data.ChampionRepository
import com.adisastrawan.jetpacksubmission.model.Champion
import com.adisastrawan.jetpacksubmission.ui.common.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: ChampionRepository) : ViewModel() {
    private var _champions: MutableStateFlow<UIState<List<Champion>>> =
        MutableStateFlow(UIState.Loading)
    val champions: StateFlow<UIState<List<Champion>>> get() = _champions

    fun getFavoriteChampions() {
        viewModelScope.launch {
            _champions.value = UIState.Loading
            repository.getFavoriteChampions().collect{
                _champions.value = UIState.Success(it)
            }
        }
    }


}