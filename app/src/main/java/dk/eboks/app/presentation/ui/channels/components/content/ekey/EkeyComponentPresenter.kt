package dk.eboks.app.presentation.ui.channels.components.content.ekey

import com.google.gson.Gson
import dk.eboks.app.domain.interactors.ekey.*
import dk.eboks.app.domain.managers.AppStateManager
import dk.eboks.app.domain.models.channel.ekey.*
import dk.eboks.app.domain.models.local.ViewError
import dk.nodes.arch.presentation.base.BasePresenterImpl
import dk.nodes.locksmith.core.preferences.EncryptedPreferences
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by bison on 20-05-2017.
 */
class EkeyComponentPresenter @Inject constructor(val appState: AppStateManager, val getMasterkeyInteractor: GetEKeyMasterkeyInteractor,
                                                 val setMasterKeyInteractor: SetEKeyMasterkeyInteractor,
                                                 val deleteMasterKeyInteractor: DeleteEKeyMasterkeyInteractor,
                                                 val getEKeyVaultInteractor: GetEKeyVaultInteractor,
                                                 val encryptedPreferences: EncryptedPreferences,
                                                 val setEKeyVaultInteractor: SetEKeyVaultInteractor,
                                                 val deleteEKeyVaultInteractor: DeleteEKeyVaultInteractor,
                                                 val gson: Gson)
    : EkeyComponentContract.Presenter, BasePresenterImpl<EkeyComponentContract.View>(),
        GetEKeyMasterkeyInteractor.Output, SetEKeyMasterkeyInteractor.Output, GetEKeyVaultInteractor.Output {
    init {
        getMasterkeyInteractor.output = this
        setMasterKeyInteractor.output = this
        getEKeyVaultInteractor.output = this
    }

    override fun onGetEKeyVaultSuccess(vault: String) {
        Timber.d("success")
    }

    override fun onGetEKeyVaultNotFound() {
        runAction { view -> view.onVaultNotFound() }
    }

    override fun onGetEKeyVaultError(viewError: ViewError) {
        Timber.d("failure")
    }

    override fun onSetEKeyMasterkeySuccess() {
        Timber.d("success")
    }

    override fun onSetEKeyMasterkeyError(viewError: ViewError) {
        Timber.d("failure")
    }

    override fun onGetEKeyMasterkeySuccess(masterkey: EKeyGetMasterkeyResponse) {
        runAction { view -> view.onMasterkey(masterkey.masterkey) }
    }

    override fun onGetEkeyMasterkeyNotFound() {
        runAction { view -> view.onMasterkeyNotFound() }
    }

    override fun onGetEKeyMasterkeyError(viewError: ViewError) {
        runAction { view -> view.onGetMasterkeyError(viewError) }
    }

    override fun storeMasterkey(masterKey: String) {
        appState.state?.currentUser?.let {
            encryptedPreferences.putString("ekey_${it.id}", masterKey)
        }
    }

    override fun setMasterkey(hash: String, encrypted: String) {
        setMasterKeyInteractor.input = SetEKeyMasterkeyInteractor.Input(encrypted, hash)
        setMasterKeyInteractor.run()
    }

    override fun getMasterkey() {
        getMasterkeyInteractor.run()
    }

    override fun getVault(signatureTime: String, signature: String) {
        getEKeyVaultInteractor.input = GetEKeyVaultInteractor.Input(signatureTime, signature)
        getEKeyVaultInteractor.run()
    }

    override fun setVault(vault: String, signatureTime: String, signature: String) {
        setEKeyVaultInteractor.input = SetEKeyVaultInteractor.Input(vault, signatureTime, signature)
        setEKeyVaultInteractor.run()
    }

    override fun deleteMasterKey() {
        deleteMasterKeyInteractor.run()
    }

    override fun deleteVault(signature: String, signatureTime: String) {
        deleteEKeyVaultInteractor.input = DeleteEKeyVaultInteractor.Input(signature, signatureTime)
        deleteEKeyVaultInteractor.run()
    }

    override fun getKeys() {
        runAction { v ->
            v.showKeys(createMocks())
        }
    }

    private fun createMocks() : List<BaseEkey>{
        val keyList = mutableListOf<BaseEkey>()
        keyList.add(Login("test1@gmail.com", "gmailPW1", "Gmail", "Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet"))
        keyList.add(Pin("Peter","1234", "Visa", null))
        keyList.add(Login("test1@hotmail.com", "hotmailPW1", "Hotmail", null))
        keyList.add(Login("test1@hotmail.com", "hotmailPW1", "Hotmail", null))
        keyList.add(Login("test1@hotmail.com", "hotmailPW1", "Hotmail", null))
        keyList.add(Login("test1@hotmail.com", "hotmailPW1", "Hotmail", null))
        keyList.add(Note("Summerhouse", "Lorem ipsum dolor sit amet"))
        keyList.add(Pin("Knud","4321", "MasterCard", null))
        keyList.add(Ekey("1234","Ekey", null))

        val temp = gson.toJson(keyList)
        Timber.d(temp)

        return keyList
    }
}