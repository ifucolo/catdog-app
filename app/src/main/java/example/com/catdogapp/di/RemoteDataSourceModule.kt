package example.com.catdogapp.di

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import example.com.catdogapp.BuildConfig
import example.com.catdogapp.R
import example.com.catdogapp.api.CatApi
import example.com.catdogapp.api.DogApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val remoteDatasourceModule = module {
    single { providesGson() }
    single { providesInterceptorAuth(androidContext()) }
    single { providesOkHttpClient(get()) }

    single { createWebService<CatApi>(get(), get(), androidContext().getString(R.string.cat_url)) }
    single { createWebService<DogApi>(get(), get(), androidContext().getString(R.string.dog_url)) }
}

fun providesGson(): Gson {
    val gsonBuilder = GsonBuilder()
    return gsonBuilder.create()
}


fun providesInterceptorAuth(context: Context) = Interceptor { chain ->
    val request = chain.request()
    val builder = request.newBuilder()

    builder.header("x-api-key", context.getString(R.string.api_key))

    chain.proceed(builder.build())
}

fun providesOkHttpClient(interceptorAuth: Interceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()

    if (BuildConfig.DEBUG)
        builder.addNetworkInterceptor(StethoInterceptor())

    builder.addInterceptor(interceptorAuth)
    builder.connectTimeout(30, TimeUnit.SECONDS)
    builder.readTimeout(30, TimeUnit.SECONDS)
    builder.writeTimeout(30, TimeUnit.SECONDS)

    return builder.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, gson: Gson, url: String): T {
    return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(url)
            .client(okHttpClient)
            .build()
            .create(T::class.java)
}