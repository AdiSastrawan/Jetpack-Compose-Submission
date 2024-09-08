package com.adisastrawan.jetpacksubmission.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adisastrawan.jetpacksubmission.data.ChampionRepository
import com.adisastrawan.jetpacksubmission.ui.screen.detail.DetailViewModel
import com.adisastrawan.jetpacksubmission.ui.screen.favorite.FavoriteViewModel
import com.adisastrawan.jetpacksubmission.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: ChampionRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }else if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(repository) as T
        }else if(modelClass.isAssignableFrom(FavoriteViewModel::class.java)){
            return FavoriteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(repository: ChampionRepository): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(repository)
            }
    }
}