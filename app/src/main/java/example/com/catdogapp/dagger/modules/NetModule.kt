package example.com.catdogapp.dagger.modules

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import example.com.catdogapp.BuildConfig
import example.com.catdogapp.R
import example.com.catdogapp.api.CatApi
import example.com.catdogapp.api.DogApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule{

    @Provides
    @Singleton
    fun providesGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Named("interceptorAuth")
    @Singleton
    fun providesInterceptorAuth(context: Context) = Interceptor { chain ->
        val request = chain.request()
        val builder = request.newBuilder()

        builder.header("x-api-key", context.getString(R.string.api_key))

        chain.proceed(builder.build())
    }

    @Provides
    @Named("server_client")
    @Singleton
    fun providesOkHttpClient(@Named("interceptorAuth") interceptorAuth: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG)
            builder.addNetworkInterceptor(StethoInterceptor())

        builder.addInterceptor(interceptorAuth)
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.writeTimeout(30, TimeUnit.SECONDS)

        return builder.build()
    }

    @Provides
    @Named("cat_server")
    @Singleton
    fun provideServerRetrofit(context: Context, gson: Gson, @Named("server_client") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(context.getString(R.string.cat_url))
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Named("dog_server")
    @Singleton
    fun provideReportingRetrofit(context: Context, gson: Gson, @Named("server_client") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(context.getString(R.string.dog_url))
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideCatApi(@Named("cat_server") retrofit: Retrofit): CatApi {
        return retrofit.create(CatApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDogApi(@Named("dog_server") retrofit: Retrofit): DogApi {
        return retrofit.create(DogApi::class.java)
    }
}