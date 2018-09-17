package example.com.catdogapp.feature.home.cats.infrastructure.mapper

import example.com.catdogapp.feature.home.cats.domain.entity.CatSizeInfo
import example.com.catdogapp.feature.home.dogs.infrastructure.model.SizeInfoPayload

object SizeMapper {

    fun map(payload: SizeInfoPayload?) = CatSizeInfo(
            imperial = payload?.imperial.orEmpty(),
            metric = payload?.metric.orEmpty()
    )
}