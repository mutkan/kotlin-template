package dk.nodes.template.presentation.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import dk.nodes.template.R
import dk.nodes.template.domain.models.exceptions.ViewError
import kotlinx.android.synthetic.main.dialog_base_error.*
import java.io.PrintWriter

class BaseErrorDialog(context: Context?) : Dialog(context) {
    var viewError: ViewError? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_base_error)
        okButton.setOnClickListener {
            dismiss()
        }
    }

    override fun show() {
        viewError?.let {
            errorCode.text = it.errorCode.toString()
            errorMessage.text = it.message
            setTitle(it.title)
            val str = ""
            val pw = PrintWriter(str)
            it.throwable?.printStackTrace(pw)
            if (str.isNullOrEmpty()) {
                errorException.visibility = View.GONE
            } else {
                errorException.visibility = View.VISIBLE
                errorException.text = str
            }
            super.show()
        }
    }
}