package dk.nodes.template.presentation.ui.main

import dk.nodes.arch.presentation.base.BasePresenterImpl
import dk.nodes.template.domain.interactors.GetPostInteractorImpl
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class MainPresenter @Inject constructor(private val getPostInteractorImpl: GetPostInteractorImpl) :
        MainContract.Presenter,
        BasePresenterImpl<MainContract.View>() {

    override fun getPost() {
        launch {
            getPostInteractorImpl.run("something")
        }
    }
}