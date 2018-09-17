
package example.com.catdogapp.utill.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import example.com.catdogapp.utill.Util


inline fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

inline fun ViewGroup.findByTag(tag: String): List<View> = Util.getViewsByTag(this, tag)
