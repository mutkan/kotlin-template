package dk.nodes.template.util

import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// makes viewgroups iterable (you can for the each out of them!:)

fun ViewGroup.asSequence(): Sequence<View> = object : Sequence<View> {
    override fun iterator(): Iterator<View> = object : Iterator<View> {
        private var nextValue: View? = null
        private var done = false
        private var position: Int = 0

        override fun hasNext(): Boolean {
            if (nextValue == null && !done) {
                nextValue = getChildAt(position)
                position++
                if (nextValue == null) done = true
            }
            return nextValue != null
        }

        override fun next(): View {
            if (!hasNext()) {
                throw NoSuchElementException()
            }
            val answer = nextValue
            nextValue = null
            return answer!!
        }
    }
}

val ViewGroup.views: List<View>
    get() = asSequence().toList()

inline fun <T> T.guard(block: T.() -> Unit): T {
    if (this == null) block(); return this
}

fun View.setVisible(isVisible: Boolean, invisibleType: Int = View.GONE) {
    this.visibility = if (isVisible) {
        View.VISIBLE
    } else {
        invisibleType
    }
}


inline fun <reified T> Gson.fromJson(json: String?) = this.fromJson<T>(
        json,
        object : TypeToken<T>() {}.type
)

fun View.setDebouncedClickListener(duration: Long = 1000, listener: () -> Unit) {
    setOnClickListener(object : View.OnClickListener {
        private var lastClickMillis: Long = 0

        override fun onClick(v: View) {
            val now = SystemClock.elapsedRealtime()

            if (now - lastClickMillis > duration) {
                listener()
            }

            lastClickMillis = now
        }
    })
}