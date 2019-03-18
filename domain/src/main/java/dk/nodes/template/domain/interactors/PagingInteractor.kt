package dk.nodes.template.domain.interactors

import androidx.paging.DataSource

interface PagingInteractor<S, T> {
    fun dataSourceFactory(): DataSource.Factory<S, T>
}