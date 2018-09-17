package com.example.com.mptvshow.di

import dagger.Module
import dagger.Provides
import example.com.catdogapp.feature.home.cats.domain.CatSource
import example.com.catdogapp.feature.home.cats.infrastructure.CatInfrastructure
import example.com.catdogapp.feature.home.dogs.domain.DogSource
import example.com.catdogapp.feature.home.dogs.infrastructure.DogInfrastructure


@Module
class RepositoryModule {

    @Provides
    fun providesCatSource(catInfrastructure: CatInfrastructure): CatSource = catInfrastructure

    @Provides
    fun providesDogSource(dogInfrastructure: DogInfrastructure): DogSource = dogInfrastructure

}