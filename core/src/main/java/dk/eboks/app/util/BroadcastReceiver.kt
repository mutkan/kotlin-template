package dk.eboks.app.util

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import timber.log.Timber

/**
 * Created by bison on 15/03/16.
 */
class BroadcastReceiver {
    private val filter: IntentFilter = IntentFilter()
    private var listener: OnIntentListener? = null
    private var isRegistered: Boolean = false

    private val messageReceiver = object : android.content.BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            Timber.e("Receiving broadcast, action = %s", action)
            listener?.onIntent(intent)
        }
    }

    fun addFilter(action: String): BroadcastReceiver {
        filter.addAction(action)
        return this
    }

    fun setListener(listener: OnIntentListener): BroadcastReceiver {
        this.listener = listener
        return this
    }

    fun register(c: Context): BroadcastReceiver {
        // NLog.d(TAG, "Registering receiver.");
        LocalBroadcastManager.getInstance(c).registerReceiver(messageReceiver, filter)
        isRegistered = true
        return this
    }

    fun deregister(c: Context): BroadcastReceiver {
        // NLog.d(TAG, "Deregistering receiver.");
        LocalBroadcastManager.getInstance(c).unregisterReceiver(messageReceiver)
        isRegistered = false
        return this
    }

    interface OnIntentListener {
        fun onIntent(intent: Intent)
    }

    companion object {
        val TAG = BroadcastReceiver::class.java.simpleName

        fun broadcast(c: Context, intent: Intent) {
            Timber.e("Broadcasting action %s", intent.action)
            LocalBroadcastManager.getInstance(c).sendBroadcast(intent)
        }
    }
}