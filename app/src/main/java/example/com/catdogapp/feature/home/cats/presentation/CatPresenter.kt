package example.com.catdogapp.feature.home.cats.presentation

import example.com.catdogapp.feature.home.cats.domain.CatSource
import example.com.catdogapp.shared.ReactivePresenter
import example.com.catdogapp.shared.RxUtils
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class CatPresenter @Inject constructor(val source: CatSource): ReactivePresenter<CatView>() {

    fun getCats() {
        disposables += source.getCats()
                .compose(RxUtils.applySingleSchedulers())
                .doOnSubscribe { view?.showLoading() }
                .doFinally { view?.hideLoading() }
                .subscribe(
                        {
                            view?.showCats(it)
                        },{
                            view?.showError()
                        }
                )
    }
}
