package example.com.catdogapp

import android.app.Application
import com.facebook.stetho.Stetho
import example.com.catdogapp.di.appModule
import example.com.catdogapp.di.remoteDatasourceModule
import org.koin.android.ext.android.startKoin

class CatDogApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, remoteDatasourceModule))


        if(BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)

            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .build())
        }
    }
}