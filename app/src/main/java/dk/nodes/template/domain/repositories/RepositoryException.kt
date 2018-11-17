package dk.nodes.template.domain.repositories

class RepositoryException(code: Int, msg: String) : RuntimeException(msg) {
    companion object {
        val missingModelId = RepositoryException(401, "Missing model id")
        val modelNotFound = RepositoryException(404, "Model not found")
    }
}