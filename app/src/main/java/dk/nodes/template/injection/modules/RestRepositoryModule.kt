package dk.nodes.template.injection.modules

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dk.nodes.template.network.rest.Api
import dk.nodes.template.network.rest.RestPostRepository
import javax.inject.Singleton

@Module
class RestRepositoryModule {
    @Provides
    @Singleton
    fun providePostRepository(
        api: Api,
        gson: Gson,
        context: Context
    ): dk.nodes.template.domain.repositories.PostRepository {
        return RestPostRepository(api)
    }
}