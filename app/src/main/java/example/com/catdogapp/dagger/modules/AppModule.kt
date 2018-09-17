package example.com.catdogapp.dagger.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application){

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return app
    }

    @Provides
    @Singleton
    fun providesContext(): Context {
        return app.applicationContext
    }
}