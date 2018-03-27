package dk.eboks.app.presentation.ui.screens.profile

import android.os.Bundle
import android.support.v4.app.Fragment
import dk.eboks.app.R
import dk.eboks.app.presentation.base.BaseActivity
import dk.eboks.app.presentation.ui.components.profile.edit.ProfileInfoComponentFragment
import javax.inject.Inject

class ProfileActivity : BaseActivity(), ProfileContract.View {
    @Inject
    lateinit var presenter: ProfileContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        component.inject(this)
        presenter.onViewCreated(this, lifecycle)

        setFragment(ProfileInfoComponentFragment())
    }

    fun showMyInformationFragment() {
        //TODO setup fragment¬
    }

    fun showTerms() {
        //TODO setup fragment
    }

    fun showHelp() {
        //TODO setup fragment
    }

    fun showPrivacy() {
        //TODO setup fragment
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .setTransitionStyle(R.style.AppTheme_WindowTransition)
                .replace(R.id.profileActivityContainerFragment, fragment)
                .commit()
    }
}