package dk.nodes.template.domain.models.exceptions

data class ViewError(
        var title: String,
        var message: String,
        var errorCode: Int? = null,
        var throwable: Throwable? = null
)