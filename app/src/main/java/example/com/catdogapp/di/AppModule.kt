package example.com.catdogapp.di

import example.com.catdogapp.feature.home.tabs.cats.domain.CatSource
import example.com.catdogapp.feature.home.tabs.cats.infrastructure.CatInfrastructure
import example.com.catdogapp.feature.home.tabs.cats.presentation.CatPresenter
import example.com.catdogapp.feature.home.tabs.dogs.domain.DogSource
import example.com.catdogapp.feature.home.tabs.dogs.infrastructure.DogInfrastructure
import example.com.catdogapp.feature.home.tabs.dogs.presentation.DogPresenter
import org.koin.dsl.module.module

val appModule = module {
    single<CatSource> { CatInfrastructure(get()) }
    single<DogSource> { DogInfrastructure(get()) }

    factory { CatPresenter(get()) }
    factory { DogPresenter(get()) }
}