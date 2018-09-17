package example.com.catdogapp.api

import example.com.catdogapp.feature.home.tabs.cats.infrastructure.model.CatPayload
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {

    @GET("v1/images/search")
    fun fetchCats(@Query("limit") limit: Int): Single<List<CatPayload>>
}