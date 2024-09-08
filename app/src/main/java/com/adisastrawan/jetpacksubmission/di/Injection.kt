package com.adisastrawan.jetpacksubmission.di

import com.adisastrawan.jetpacksubmission.data.ChampionRepository

object Injection {
    fun provideRepository():ChampionRepository{
        return ChampionRepository.getInstance()
    }
}