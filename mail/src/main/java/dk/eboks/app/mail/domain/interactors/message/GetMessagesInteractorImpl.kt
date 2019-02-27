package dk.eboks.app.mail.domain.interactors.message

import dk.eboks.app.domain.models.folder.Folder
import dk.eboks.app.domain.models.folder.FolderType
import dk.eboks.app.domain.models.local.ViewError
import dk.eboks.app.domain.models.message.Message
import dk.eboks.app.domain.models.sender.Sender
import dk.eboks.app.domain.repositories.MessagesRepository
import dk.eboks.app.util.exceptionToViewError
import dk.eboks.app.util.guard
import dk.nodes.arch.domain.executor.Executor
import dk.nodes.arch.domain.interactor.BaseInteractor
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by bison on 01/02/18.
 */
internal class GetMessagesInteractorImpl @Inject constructor(
    executor: Executor,
    private val messagesRepository: MessagesRepository
) :
    BaseInteractor(executor), GetMessagesInteractor {
    override var output: GetMessagesInteractor.Output? = null
    override var input: GetMessagesInteractor.Input? = null

    override fun execute() {
        try {
            input?.let { args ->
                args.folder?.let { folder ->
                    if (folder.id != 0)
                        getMessageFolder(folder, args.offset, args.limit)
                    else
                        getMessages(false, folder)
                }.guard {
                    args.sender?.let { sender ->
                        getMessageSender(sender, args.offset, args.limit)
                    }.guard {
                        runOnUIThread {
                            output?.onGetMessagesError(ViewError())
                        }
                    }
                }
            }
        } catch (t: Throwable) {
            Timber.e(t)
            runOnUIThread {
                output?.onGetMessagesError(exceptionToViewError(t, shouldClose = true))
            }
        }
    }

    private fun getMessageFolder(folder: Folder, offset: Int = 0, limit: Int = 20) {
        Timber.d("Fetching folder (${folder.id} : ${folder.name}) messages $offset $limit")
        val messages = messagesRepository.getMessagesByFolder(folder.id, offset, limit)
        runOnUIThread { output?.onGetMessages(messages) }
    }

    private fun getMessageSender(sender: Sender, offset: Int = 0, limit: Int = 20) {
        Timber.d("Fetching sender (${sender.id} : ${sender.name}) messages $offset $limit")
        val messages = messagesRepository.getMessagesBySender(sender.id, offset, limit)
        runOnUIThread { output?.onGetMessages(messages) }
    }

    /*
    private fun getMessageFolder(folder: Folder, offset : Int = 0, limit : Int = 20)
    {
        val hasCached = if(input?.cached == true)
            messagesRepository.hasCachedMessageFolder(folder)
        else
            false

        val messages = getMessages(hasCached, folder)
        runOnUIThread {
            output?.onGetMessages(messages)
        }

        if(hasCached)
            Timber.e("Emitting cached messages")
        else {
            Timber.e("Emitting fresh messages")
            return
        }

        if(hasCached)
        {
            val fresh_msgs = getMessages(false, folder)
            runOnUIThread {
                Timber.e("Emitting refreshed messages")
                output?.onGetMessages(fresh_msgs)
            }
        }
    }

    private fun getMessageSender(sender: Sender, offset : Int = 0, limit : Int = 20)
    {
        val hasCached = if(input?.cached == true)
            messagesRepository.hasCachedMessageSender(sender)
        else
            false

        val messages = messagesRepository.getMessagesBySender(hasCached, sender.id)
        runOnUIThread {
            output?.onGetMessages(messages)
        }

        if(hasCached)
            Timber.e("Emitting cached messages")
        else {
            Timber.e("Emitting fresh messages")
            return
        }

        if(hasCached)
        {
            val fresh_msgs = messagesRepository.getMessagesBySender(false, sender.id)
            runOnUIThread {
                Timber.e("Emitting refreshed messages")
                output?.onGetMessages(fresh_msgs)
            }
        }
    }
    */

    private fun getMessages(cached: Boolean, folder: Folder) {
        var result: List<Message> = ArrayList()
        folder.type?.let { type ->
            when (type) {
                FolderType.HIGHLIGHTS -> {
                    try {
                        result = messagesRepository.getHighlights(cached)
                    } catch (t: Throwable) {
                        t.printStackTrace()
                    }
                }
                FolderType.LATEST -> {
                    result = messagesRepository.getLatest(cached)
                }
                FolderType.UNREAD -> {
                    result = messagesRepository.getUnread(cached)
                }
                FolderType.UPLOADS -> {
                    result = messagesRepository.getUploads(cached)
                }
                else -> {
                    Timber.e("No API call exists to fetch messages of type $type")
                }
            }
        }

        runOnUIThread {
            output?.onGetMessages(result)
        }
    }
}