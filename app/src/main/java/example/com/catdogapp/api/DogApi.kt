package example.com.catdogapp.api

import example.com.catdogapp.feature.home.tabs.dogs.infrastructure.model.DogPayload
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DogApi {

    @GET("v1/images/search")
    fun fetchDogs(@Query("limit") limit: Int): Single<List<DogPayload>>
}