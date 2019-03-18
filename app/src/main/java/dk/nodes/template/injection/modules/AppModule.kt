package dk.nodes.template.injection.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dk.nodes.template.App
import dk.nodes.template.BuildConfig
import dk.nodes.template.inititializers.AppInitializers
import dk.nodes.template.inititializers.NStackInitializer
import dk.nodes.template.inititializers.TimberInitializer
import javax.inject.Named

@Module
class AppModule {
    @Provides
    fun provideContext(application: App): Context = application.applicationContext


    @Provides
    @Named("API_KEY")
    fun provideApiKey(): String {
        return BuildConfig.API_KEY
    }

    @Provides
    fun provideInitializers(
        nStackInitializer: NStackInitializer,
        timberInitializer: TimberInitializer
    ): AppInitializers {
        return AppInitializers(nStackInitializer, timberInitializer)
    }
}