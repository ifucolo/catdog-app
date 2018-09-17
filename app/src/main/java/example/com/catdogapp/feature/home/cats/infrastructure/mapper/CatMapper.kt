package example.com.catdogapp.feature.home.cats.infrastructure.mapper

import example.com.catdogapp.feature.home.cats.domain.entity.CatDetail
import example.com.catdogapp.feature.home.cats.infrastructure.model.CatPayload

object CatMapper {

    fun map(payload: CatPayload) = CatDetail(
            id = payload.id,
            imgUrl = payload.imgUrl.orEmpty(),
            breedId = payload.breed?.firstOrNull()?.id.orEmpty(),
            name = payload.breed?.firstOrNull()?.name.orEmpty(),
            lifeSpan = payload.breed?.firstOrNull()?.lifeSpan.orEmpty(),
            breedFor = payload.breed?.firstOrNull()?.breedFor.orEmpty(),
            breedGroup = payload.breed?.firstOrNull()?.breedGroup.orEmpty(),
            temperament = payload.breed?.firstOrNull()?.temperament.orEmpty(),
            weight = SizeMapper.map(payload.breed?.firstOrNull()?.weight),
            height = SizeMapper.map(payload.breed?.firstOrNull()?.height)
    )

    fun map(payloads: List<CatPayload>) = payloads.map { map(it) }
}