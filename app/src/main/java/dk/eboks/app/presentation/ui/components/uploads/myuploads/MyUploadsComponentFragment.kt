package dk.eboks.app.presentation.ui.components.uploads.myuploads

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import dk.eboks.app.App
import dk.eboks.app.R
import dk.eboks.app.domain.managers.EboksFormatter
import dk.eboks.app.domain.models.Translation
import dk.eboks.app.domain.models.folder.Folder
import dk.eboks.app.domain.models.message.Message
import dk.eboks.app.domain.models.message.MessageType
import dk.eboks.app.domain.models.shared.Status
import dk.eboks.app.presentation.base.BaseFragment
import dk.eboks.app.presentation.ui.components.mail.maillist.MailListComponentContract
import dk.eboks.app.presentation.ui.screens.Overlay.OverlayActivity
import dk.eboks.app.util.guard
import kotlinx.android.synthetic.main.fragment_upload_myuploadoverview.*
import kotlinx.android.synthetic.main.include_toolbar.*
import kotlinx.coroutines.experimental.delay
import java.util.*
import javax.inject.Inject

/**
 * Created by bison on 09-02-2018.
 */
class MyUploadsComponentFragment : BaseFragment(), MyUploadsComponentContract.View {

    var checkedList: MutableList<Message> = ArrayList()
    //mock stuff
    var numberOfMocks = 10
    var uploads: MutableList<Message> = ArrayList()
    var modeEdit: Boolean = false
    var handler = Handler()

    @Inject
    lateinit var mailPresenter: MailListComponentContract.Presenter
    @Inject
    lateinit var presenter: MyUploadsComponentContract.Presenter
    @Inject
    lateinit var formatter: EboksFormatter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_upload_myuploadoverview, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)
        presenter.onViewCreated(this, lifecycle)
        setupRecyclerView()
        setupRows()
        setupTopBar()
        setupFab()
        checkFabState()

    }

    private fun setupFab() {
        mainFab.setOnClickListener {

            startActivityForResult(Intent(context,OverlayActivity::class.java),1)


        }
    }

    private fun setupTopBar() {
        val menuProfile = getBaseActivity()?.mainTb?.menu?.add(Translation.uploads.topbarEdit)
        menuProfile?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menuProfile?.setOnMenuItemClickListener { item: MenuItem ->
            switchMode()
            true
        }
    }

    private fun switchMode() {
        modeEdit = !modeEdit
        checkedList.clear()
        uploadsRv.adapter.notifyDataSetChanged()
    }

    fun setupRecyclerView() {
        uploadsRv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        uploadsRv.adapter = UploadsAdapter(uploads)
    }

    private fun createmock(numberOfMocks: Int) {
        for (i in 1..numberOfMocks) {
            val random = Random()
            var unread = (random.nextInt(i) == 0)

            var randomStatus = Status(false, "important title", "important text", 0, Date())
            if (random.nextInt(i) == 0) {
                randomStatus.important = true
            }
            uploads.add(dk.eboks.app.domain.models.message.Message("17", "subject" + i, Date(), unread, null, MessageType.UPLOAD, null, Folder(1), null, null, 0, null, null, null, null, randomStatus, "note string"))
        }
    }

    fun setupRows() {
        uploads.clear()
        createmock(numberOfMocks)
        uploadsRv.adapter.notifyDataSetChanged()
    }

    private fun checkFabState() {
        if (checkedList.size > 0) {
            mainFab.show()
        } else {
            mainFab.hide()
        }
    }

    override fun showProgress(show: Boolean) {

    }

    override fun showEmpty(show: Boolean) {

    }

    override fun showRefreshProgress(show: Boolean) {

    }

    override fun showMessages(messages: List<Message>) {

    }

    // Adapter and viewholder
    inner class UploadsAdapter(val uploads: List<dk.eboks.app.domain.models.message.Message>) : RecyclerView.Adapter<UploadsAdapter.UploadsViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UploadsViewHolder {
            return UploadsViewHolder(inflator.inflate(R.layout.viewholder_upload_myuploads_row, parent, false))
        }

        override fun onBindViewHolder(holder: UploadsViewHolder?, position: Int) {
            var last = (position == uploads.size)
            holder?.bind(uploads[position], last)
        }

        override fun getItemCount(): Int {
            return uploads.size
        }

        inner class UploadsViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
            val headerTv = v.findViewById<TextView>(R.id.headerTv)
            val subHeaderTv = v.findViewById<TextView>(R.id.subHeaderTv)
            val dateTv = v.findViewById<TextView>(R.id.dateTv)
            val dividerV = v.findViewById<View>(R.id.dividerV)
            //todo change imageButton to checkbox
            val checkBox = v.findViewById<ImageButton>(R.id.checkboxIb)
            val uploadFl = v.findViewById<FrameLayout>(R.id.uploadFl)


            fun bind(currentItem: Message, last: Boolean) {
                headerTv.text = currentItem.id
                subHeaderTv.text = currentItem.subject
                dateTv.text = formatter.formatDateRelative(currentItem)

                if (last) {
                    dividerV.visibility = View.GONE
                }
                if (modeEdit) {
                    uploadFl.visibility = View.GONE
                    checkBox.visibility = View.VISIBLE
                } else {
                    uploadFl.visibility = View.VISIBLE
                    checkBox.visibility = View.GONE
                }


                v.setOnClickListener {
                    if (checkBox.visibility == View.VISIBLE) {
                        //adding or removing item to checked list
                        if (!checkBox.isSelected) {
                            checkedList.add(currentItem)
                        } else {
                            checkedList.remove(currentItem)
                        }

                        // UI
                        checkBox.isSelected = !checkBox.isSelected
                        checkFabState()

                        if (uploadFl.visibility == View.VISIBLE) {
                            mailPresenter.openMessage(currentItem)
                        }
                    }

                }
            }
        }
    }
}
