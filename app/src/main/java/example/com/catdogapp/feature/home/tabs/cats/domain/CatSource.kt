package example.com.catdogapp.feature.home.tabs.cats.domain

import example.com.catdogapp.feature.home.tabs.cats.domain.entity.CatDetail
import io.reactivex.Single

interface CatSource {
    fun getCats(): Single<List<CatDetail>>
}