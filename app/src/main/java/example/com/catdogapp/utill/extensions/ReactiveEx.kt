package example.com.catdogapp.utill.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

 operator fun CompositeDisposable.plusAssign(subscribe: Disposable?) {}
