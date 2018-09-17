package example.com.catdogapp.feature.home.tabs.dogs.infrastructure

import example.com.catdogapp.api.DogApi
import example.com.catdogapp.feature.home.tabs.cats.infrastructure.mapper.CatMapper
import example.com.catdogapp.feature.home.tabs.dogs.domain.DogSource
import example.com.catdogapp.feature.home.tabs.dogs.domain.entity.DogDetail
import example.com.catdogapp.feature.home.tabs.dogs.infrastructure.mapper.DogMapper
import io.reactivex.Single
import javax.inject.Inject

class DogInfrastructure @Inject constructor(private val dogApi: DogApi): DogSource {

    companion object {
        const val LIMIT = 50
    }

    override fun getDogs(): Single<List<DogDetail>> {
        return dogApi.fetchDogs(LIMIT)
                .map { DogMapper.map(it) }
    }
}