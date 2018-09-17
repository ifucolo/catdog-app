package example.com.catdogapp.feature.home.cats.infrastructure.model

import com.google.gson.annotations.SerializedName
import example.com.catdogapp.feature.home.dogs.infrastructure.model.BreedPayload

class CatPayload(
        @SerializedName("id") val id: String,
        @SerializedName("url") val imgUrl: String,
        @SerializedName("breeds") val breed: List<BreedPayload>? = null
)
