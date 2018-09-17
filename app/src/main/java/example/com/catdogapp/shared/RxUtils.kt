package example.com.catdogapp.shared

import io.reactivex.CompletableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

object RxUtils {

    fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> applySingleSchedulers(): SingleTransformer<T, T> {
        return SingleTransformer {
            it.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun applyCompletableSchedulers(): CompletableTransformer {
        return CompletableTransformer {
            it.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> replayOnError(attempts: Int = 10): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.retryWhen {
                it.zipWith(Observable.range(1, attempts+1))
                    .flatMap { (throwable, attempt) ->
                        if (attempt >= attempts)
                            return@flatMap Observable.error<Long>(throwable).cast(Long::class.java)

                        Observable.timer(attempt * 200L, TimeUnit.MILLISECONDS)
                    }
            }
        }
    }

    fun <T> replayOnErrorFixTime(attempts: Int = 10, delay: Long = 500L, predicate: ((Throwable) -> Boolean)? = null): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.retryWhen {
                it.zipWith(Observable.range(1, attempts+1))
                    .flatMap { (throwable, attempt) ->
                        if (attempt>= attempts || predicate?.invoke(throwable) == false)
                            return@flatMap Observable.error<Long>(throwable).cast(Long::class.java)

                        Observable.timer(delay, TimeUnit.MILLISECONDS)
                    }
            }
        }
    }

}