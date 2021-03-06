package example.com.catdogapp.feature.home.tabs.cats.presentation

import example.com.catdogapp.feature.home.tabs.cats.domain.CatSource
import example.com.catdogapp.shared.presentation.ReactivePresenter
import example.com.catdogapp.utill.RxUtils
import io.reactivex.rxkotlin.plusAssign

class CatPresenter constructor(val source: CatSource): ReactivePresenter<CatView>() {

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
