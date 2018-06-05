package dk.eboks.app.presentation.ui.screens.channels.content.storebox

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import dk.eboks.app.R
import dk.eboks.app.domain.models.Translation
import dk.eboks.app.domain.models.shared.Link
import dk.eboks.app.presentation.base.BaseActivity
import dk.eboks.app.presentation.base.BaseFragment
import dk.eboks.app.presentation.base.BaseWebFragment
import dk.eboks.app.util.putArg
import kotlinx.android.synthetic.main.fragment_base_web.*
import kotlinx.android.synthetic.main.include_toolbar.*
import timber.log.Timber
import javax.inject.Inject

class StoreboxAddCardActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(dk.eboks.app.R.layout.activity_storebox_add_card)
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                if (!isDestroyed)
                    finish()
            }
        }
        intent?.getSerializableExtra(Link::class.java.simpleName)?.let { link ->
            setRootFragment(R.id.containerFl, StoreboxAddCardFragment().putArg(Link::class.java.simpleName, link) as BaseFragment)
        }

    }

    class StoreboxAddCardFragment : BaseWebFragment()
    {
        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            arguments?.getSerializable(Link::class.java.simpleName)?.let { link ->
                webView.loadUrl((link as Link).url)
            }
            setupTopBar()
        }

        // shamelessly ripped from chnt
        private fun setupTopBar() {
            mainTb.setNavigationIcon(R.drawable.icon_48_chevron_left_red_navigationbar)
            mainTb.setNavigationOnClickListener {
                activity.onBackPressed()
            }
            mainTb.title = Translation.channelsettingsstoreboxadditions.addCardTitle
        }

        override fun onOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            Timber.e("URL override: $url")
            return false
        }

        override fun onLoadFinished(view: WebView?, url: String?) {

        }

    }
}
