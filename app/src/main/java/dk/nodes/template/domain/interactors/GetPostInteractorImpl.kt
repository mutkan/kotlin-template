package dk.nodes.template.domain.interactors

import dk.nodes.template.domain.interactors.base.BaseResultInteractor
import dk.nodes.template.domain.models.Post
import dk.nodes.template.domain.repositories.PostRepository
import javax.inject.Inject

class GetPostInteractorImpl @Inject constructor(private val postRepository: PostRepository) :
        BaseResultInteractor<String, Post>() {


    override suspend fun execute(arg: String): Post {
        return postRepository.getPost(arg)
    }

}