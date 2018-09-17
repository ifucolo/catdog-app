package example.com.catdogapp.feature.home.dogs.infrastructure.model

import com.google.gson.annotations.SerializedName

class DogPayload(
        @SerializedName("id") val id: String,
        @SerializedName("url") val imgUrl: String? = null,
        @SerializedName("breeds") val breed: List<BreedPayload>? = null
)
