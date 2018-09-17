
package example.com.catdogapp.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


inline fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}
