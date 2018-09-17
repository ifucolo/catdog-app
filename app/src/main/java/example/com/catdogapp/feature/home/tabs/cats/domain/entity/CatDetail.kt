package example.com.catdogapp.feature.home.tabs.cats.domain.entity

import java.io.Serializable

class CatDetail(
    val id: String,
    val imgUrl: String,
    val breedId: String,
    val name: String,
    val lifeSpan: String,
    val breedFor: String,
    val breedGroup: String,
    val temperament: String,
    val weight: CatSizeInfo,
    val height: CatSizeInfo,
    val hasBreedInfo: Boolean
): Serializable