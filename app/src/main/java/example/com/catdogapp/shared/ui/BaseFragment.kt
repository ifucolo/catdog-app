package example.com.catdogapp.shared.ui

import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseFragment: Fragment() {

    private var disposables: CompositeDisposable? = null

    protected fun addDisposable(disposable: Disposable) {
        if (disposables == null)
            disposables = CompositeDisposable()

        disposables!!.add(disposable)
    }

    override fun onDestroyView() {
        if (disposables != null) {
            disposables!!.clear()
        }

        disposables = null

        super.onDestroyView()
    }

}