package dk.nodes.template.domain.repositories

import dk.nodes.template.domain.models.Post

interface PostRepository {
    fun getPostById(id: String?): Post
    fun getPosts(cached: Boolean = false): List<Post>
}