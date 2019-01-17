package dk.eboks.app.domain.interactors.authentication.mobileacces

import dk.eboks.app.domain.models.login.ActivationDevice
import dk.eboks.app.domain.repositories.SettingsRepository
import dk.eboks.app.network.Api
import dk.eboks.app.util.exceptionToViewError
import dk.nodes.arch.domain.executor.Executor
import dk.nodes.arch.domain.interactor.BaseInteractor

class ActivateDeviceInteractorImpl(executor: Executor, val settingsRepository: SettingsRepository, val api : Api) : BaseInteractor(executor), ActivateDeviceInteractor {
    override var input: ActivateDeviceInteractor.Input? = null
    override var output: ActivateDeviceInteractor.Output? = null

    override fun execute() {
        try {
            input?.let {

                val deviceId = settingsRepository.get().deviceId
                val deviceName = "test device"
                val os = "Android"
                val key = it.key

                val userResult = api.activateDevice(ActivationDevice(deviceId, deviceName, os, key)).execute()
                runOnUIThread { output?.onActivateDeviceSuccess() }
            }
        } catch (t: Throwable) {
            runOnUIThread {
                output?.onActivateDeviceError(exceptionToViewError(t), input?.key)
            }
        }
    }
}