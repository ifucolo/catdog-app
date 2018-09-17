package com.example.com.mptvshow.di

import dagger.Module
import dagger.Provides
import example.com.catdogapp.feature.home.tabs.cats.domain.CatSource
import example.com.catdogapp.feature.home.tabs.cats.infrastructure.CatInfrastructure
import example.com.catdogapp.feature.home.tabs.dogs.domain.DogSource
import example.com.catdogapp.feature.home.tabs.dogs.infrastructure.DogInfrastructure


@Module
class RepositoryModule {

    @Provides
    fun providesCatSource(catInfrastructure: CatInfrastructure): CatSource = catInfrastructure

    @Provides
    fun providesDogSource(dogInfrastructure: DogInfrastructure): DogSource = dogInfrastructure

}