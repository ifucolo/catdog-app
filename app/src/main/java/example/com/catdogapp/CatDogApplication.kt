package example.com.catdogapp

import android.app.Application
import com.facebook.stetho.Stetho
import example.com.catdogapp.dagger.ApplicationComponent
import example.com.catdogapp.dagger.DaggerApplicationComponent
import example.com.catdogapp.dagger.modules.AppModule

class CatDogApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    companion object {

        private lateinit var instance: CatDogApplication

        fun get(): CatDogApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        applicationComponent = DaggerApplicationComponent.builder()
                .appModule(AppModule(this))
                .build()

        applicationComponent.inject(this)


        if(BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)

            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .build())
        }
    }
}