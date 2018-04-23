package dk.eboks.app.presentation.ui.screens.message.reply

import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import dk.eboks.app.R
import dk.eboks.app.domain.models.formreply.FormInput

class LinkFormInput(formInput: FormInput, inflater: LayoutInflater) : ReplyFormInput(formInput, inflater)
{
    var linkBtn : Button? = null

    override fun buildView(vg : ViewGroup): View {
        val v = inflater.inflate(R.layout.form_input_link, vg, false)
        linkBtn = v.findViewById(R.id.linkBtn)
        linkBtn?.text = formInput.label
        linkBtn?.setOnClickListener {
            formInput.value?.let { openUrlExternal(it) }
        }
        return v
    }

    private fun openUrlExternal(url : String)
    {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        if (intent.resolveActivity(inflater.context.packageManager) != null) {
            startActivity(inflater.context, intent, null)
        }
    }
}