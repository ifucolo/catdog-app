package example.com.catdogapp.feature.home.tabs.dogs.presentation

import example.com.catdogapp.feature.home.tabs.dogs.domain.DogSource
import example.com.catdogapp.shared.presentation.ReactivePresenter
import example.com.catdogapp.utill.RxUtils
import io.reactivex.rxkotlin.plusAssign

class DogPresenter constructor(val source: DogSource): ReactivePresenter<DogView>() {

    fun getDogs() {
        disposables += source.getDogs()
                .compose(RxUtils.applySingleSchedulers())
                .doOnSubscribe { view?.showLoading() }
                .doFinally { view?.hideLoading() }
                .subscribe(
                        {
                            view?.showDogs(it)
                        },{
                            view?.showError()
                        }
                )
    }
}
