package example.com.catdogapp.feature.home.cats.infrastructure

import example.com.catdogapp.api.CatApi
import example.com.catdogapp.feature.home.cats.domain.CatSource
import example.com.catdogapp.feature.home.cats.domain.entity.CatDetail
import example.com.catdogapp.feature.home.cats.infrastructure.mapper.CatMapper
import io.reactivex.Single
import javax.inject.Inject

class CatInfrastructure @Inject constructor(private val catApi: CatApi): CatSource {

    companion object {
        const val LIMIT = 25
    }

    override fun getCats(): Single<List<CatDetail>> {
        return catApi.fetchCats(LIMIT)
                .map { CatMapper.map(it) }
    }
}