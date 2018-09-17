package example.com.catdogapp.feature.home.tabs.dogs.domain

import example.com.catdogapp.feature.home.tabs.dogs.domain.entity.DogDetail
import io.reactivex.Single

interface DogSource {
    fun getDogs(): Single<List<DogDetail>>
}