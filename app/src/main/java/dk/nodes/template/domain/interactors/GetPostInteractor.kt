package dk.nodes.template.domain.interactors

import dk.nodes.arch.domain.interactor.Interactor
import dk.nodes.template.domain.interactors.base.Interactor
import dk.nodes.template.domain.models.Post
import dk.nodes.template.domain.models.exceptions.ViewError

interface GetPostInteractor : Interactor<String> {
    interface Output {
        fun onGetPostSuccess(post: Post)
        fun onGetPostError(viewError: ViewError)
    }

    var input: Input?
    var output: Output?

    data class Input(val id: String)
}