package example.com.catdogapp.dagger

import com.example.com.mptvshow.di.RepositoryModule
import dagger.Component
import example.com.catdogapp.CatDogApplication
import example.com.catdogapp.dagger.modules.AppModule
import example.com.catdogapp.dagger.modules.NetModule
import example.com.catdogapp.feature.home.tabs.cats.ui.CatTabFragment
import example.com.catdogapp.feature.home.tabs.dogs.ui.DogTabFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (NetModule::class), (RepositoryModule::class)])
interface ApplicationComponent {
    fun inject(catDogApplication: CatDogApplication)
    fun inject(catTabFragment: CatTabFragment)
    fun inject(dogTabFragment: DogTabFragment)

}