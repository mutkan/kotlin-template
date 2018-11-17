package dk.nodes.template.presentation.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import dk.nodes.arch.presentation.base.BaseView
import dk.nodes.nstack.kotlin.inflater.NStackBaseContext
import dk.nodes.template.App
import dk.nodes.template.domain.models.ViewError
import dk.nodes.template.injection.modules.PresentationModule
import timber.log.BuildConfig
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity(), BaseView {
    open val component by lazy {
        // Todo investigate a better way to do this
        App.instance().appComponent.plus(PresentationModule())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Make sure all of our dependencies get injected
        injectDependencies()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(NStackBaseContext(newBase))
    }

    open fun showError(viewError: ViewError, forceBack: Boolean) {
        Timber.e("Error: ${viewError.message}")
        Timber.e(viewError.throwable)

        val message = if (BuildConfig.DEBUG) {
            viewError.throwable?.message ?: ""
        } else {
            viewError.message
        }

        AlertDialog.Builder(this)
                .setTitle(viewError.title)
                .setMessage(message)
                .setPositiveButton("Ok") { dialog, _ ->
                    dialog.dismiss()
                    if (forceBack) {
                        onBackPressed()
                    }
                }
                .create()
                .show()
    }

    protected abstract fun injectDependencies()
}