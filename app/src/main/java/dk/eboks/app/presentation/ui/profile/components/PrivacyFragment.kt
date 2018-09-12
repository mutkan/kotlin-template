package dk.eboks.app.presentation.ui.profile.components

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import dk.eboks.app.R
import dk.eboks.app.domain.models.Translation
import dk.eboks.app.presentation.base.BaseWebFragment
import kotlinx.android.synthetic.main.fragment_base_web.*
import javax.inject.Inject
import kotlinx.android.synthetic.main.include_toolbar.*

/**
 * Created by Christian on 5/23/2018.
 * @author   Christian
 * @since    5/23/2018.
 */
class PrivacyFragment: BaseWebFragment(), PrivacyContract.View {

    @Inject
    lateinit var presenter : PrivacyContract.Presenter

    override fun onOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return true
    }

    override fun onLoadFinished(view: WebView?, url: String?) {
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        component.inject(this)
        presenter.onViewCreated(this, lifecycle)

        mainTb.title = Translation.profile.privacyStatement
        mainTb.setNavigationIcon(R.drawable.icon_48_chevron_left_red_navigationbar)
        mainTb.setNavigationOnClickListener {
            activity.onBackPressed()
        }
    }

    override fun loadUrl(urlString: String) {
        webView.loadUrl(urlString)
    }

    override fun loadData(data : String)
    {
        webView.loadData(data, "text/html", "utf8")
    }

}