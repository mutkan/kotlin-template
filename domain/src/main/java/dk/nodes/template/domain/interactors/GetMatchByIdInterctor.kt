package dk.nodes.template.domain.interactors

import kotlinx.coroutines.CoroutineDispatcher
import techprague.nodes.dk.base.AppCoroutineDispatchers
import techprague.nodes.dk.data.apis.MatchesApi
import techprague.nodes.dk.data.models.body.MatchById
import javax.inject.Inject

class GetMatchByIdInterctor @Inject constructor(
    private val dispatchers: AppCoroutineDispatchers,
    private val matchesApi: MatchesApi
) : ResultInteractor<Long, MatchById> {
    override val dispatcher: CoroutineDispatcher
        get() = dispatchers.io

    override suspend fun invoke(executeParams: Long, onResult: (Result<MatchById>) -> Unit) {
        try {
            val result = matchesApi.matchesMatchIdGet(executeParams).execute()
            onResult(Result.success(result.body()!!))
        } catch (e: Exception) {
            onResult(Result.failure(e))
        }
    }
}