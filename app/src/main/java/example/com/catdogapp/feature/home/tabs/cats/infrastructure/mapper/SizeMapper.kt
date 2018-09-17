package example.com.catdogapp.feature.home.tabs.cats.infrastructure.mapper

import example.com.catdogapp.feature.home.tabs.cats.domain.entity.CatSizeInfo
import example.com.catdogapp.feature.home.tabs.dogs.infrastructure.model.SizeInfoPayload

object SizeMapper {

    fun map(payload: SizeInfoPayload?) = CatSizeInfo(
            imperial = payload?.imperial.orEmpty(),
            metric = payload?.metric.orEmpty()
    )
}