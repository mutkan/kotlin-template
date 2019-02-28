package dk.eboks.app.profile.interactors

import dk.eboks.app.domain.managers.AppStateManager
import dk.eboks.app.domain.managers.UserSettingsManager
import dk.nodes.arch.domain.executor.Executor
import dk.nodes.arch.domain.interactor.BaseInteractor
import javax.inject.Inject

/**
 * @author Christian
 * @since 6/19/2018.
 */
internal class SaveUserSettingsInteractorImpl @Inject constructor(
    executor: Executor,
    private val appState: AppStateManager,
    private val userSettingsManager: UserSettingsManager
) : BaseInteractor(executor), SaveUserSettingsInteractor {
    override var output: SaveUserSettingsInteractor.Output? = null
    override var input: SaveUserSettingsInteractor.Input? = null

    override fun execute() {
        // we don't use input in this example but we could:
        try {
            input?.userSettings?.let { settings ->
                userSettingsManager.put(settings)
                appState.state?.currentSettings = settings
                runOnUIThread {
                    output?.onSaveSettings()
                }
            }
        } catch (t: Throwable) {
        }
    }
}