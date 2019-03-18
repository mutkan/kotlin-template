package dk.nodes.template.injection.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dk.nodes.template.domain.managers.PrefManager
import dk.nodes.template.storage.PrefManagerImpl
import javax.inject.Singleton

@Module
class StorageModule {
    @Provides
    @Singleton
    fun providePrefManager(context: Context): PrefManager {
        return PrefManagerImpl(context)
    }
}