package example.com.catdogapp.feature.home.dogs.infrastructure.model

import com.google.gson.annotations.SerializedName

class BreedPayload(
        @SerializedName("id")  val id: String,
        @SerializedName("name")  val name: String,
        @SerializedName("life_span")  val lifeSpan: String,
        @SerializedName("bred_for")  val breedFor: String,
        @SerializedName("breed_group") val breedGroup: String,
        @SerializedName("temperament") val temperament: String,
        @SerializedName("weight") val weight: SizeInfoPayload,
        @SerializedName("height")  val height: SizeInfoPayload
)

class SizeInfoPayload(
        @SerializedName("imperial") val imperial: String,
        @SerializedName("metric") val metric: String
)