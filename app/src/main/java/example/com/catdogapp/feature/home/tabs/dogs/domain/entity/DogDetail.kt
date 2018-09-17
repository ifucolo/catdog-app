package example.com.catdogapp.feature.home.tabs.dogs.domain.entity

import java.io.Serializable

class DogDetail(
        val id: String,
        val imgUrl: String,
        val breedId: String,
        val name: String,
        val lifeSpan: String,
        val breedFor: String,
        val breedGroup: String,
        val temperament: String,
        val weight: DogSizeInfo,
        val height: DogSizeInfo,
        val hasBreedInfo: Boolean
): Serializable