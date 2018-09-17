package example.com.catdogapp.feature.home.tabs.dogs.infrastructure.mapper

import example.com.catdogapp.feature.home.tabs.dogs.domain.entity.DogSizeInfo
import example.com.catdogapp.feature.home.tabs.dogs.infrastructure.model.SizeInfoPayload

object SizeMapper {

    fun map(payload: SizeInfoPayload?) = DogSizeInfo(
            imperial = payload?.imperial.orEmpty(),
            metric = payload?.metric.orEmpty()
    )
}