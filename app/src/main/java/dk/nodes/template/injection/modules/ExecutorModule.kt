package dk.nodes.template.injection.modules

import dagger.Module
import dagger.Provides
import dk.nodes.arch.domain.executor.Executor
import dk.nodes.arch.domain.executor.ThreadExecutor
import kotlinx.coroutines.Dispatchers
import techprague.nodes.dk.base.AppCoroutineDispatchers
import javax.inject.Singleton

@Module
class ExecutorModule {
    @Provides
    @Singleton
    fun provideExecutor(): Executor {
        return ThreadExecutor()
    }

    @Provides
    @Singleton
    fun provideDispatachers(): AppCoroutineDispatchers {
        return AppCoroutineDispatchers(
            io = Dispatchers.IO,
            computation = Dispatchers.Default,
            main = Dispatchers.Main
        )
    }
}