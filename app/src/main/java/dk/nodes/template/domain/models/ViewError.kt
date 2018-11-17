package dk.nodes.template.domain.models

data class ViewError(
        var title: String,
        var message: String,
        var errorCode: Int? = null,
        var throwable: Throwable? = null
)