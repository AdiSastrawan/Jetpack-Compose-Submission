package com.adisastrawan.jetpacksubmission.data

import com.adisastrawan.jetpacksubmission.model.Champion
import com.adisastrawan.jetpacksubmission.model.FakeData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ChampionRepository {
    private val champions = ArrayList<Champion>()
    private val favoriteChampions = ArrayList<Champion>()
    init {
        if (champions.isEmpty()) {
            FakeData.champions.forEach {
                champions.add(it)
            }
        }
    }
    fun getAllChampions(): Flow<List<Champion>> {
        return flowOf(champions)
    }

    fun getChampionByName(name: String): Champion {
        return champions.first {
            it.name.lowercase() == name.lowercase()
        }
    }

    fun searchChampion(query: String): List<Champion> {
        return champions.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun getFavoriteChampions(): Flow<List<Champion>> {
        return flowOf(favoriteChampions)
    }
    fun addFavoriteChampion(champion: Champion) {
        favoriteChampions.add(champion)
    }
    fun removeFavoriteChampion(champion: Champion) {
        favoriteChampions.remove(champion)
    }
    fun isFavoriteChampion(name: String): Boolean {
        val favChampion = favoriteChampions.filter {
            it.name.contains(name, ignoreCase = true)
        }
        return favChampion.isNotEmpty()
    }
    companion object{
        @Volatile
        private var instance:ChampionRepository? = null

        fun getInstance():ChampionRepository{
            return  instance ?: synchronized(this){
                instance ?: ChampionRepository().apply {
                    instance = this
                }
            }
        }
    }
}