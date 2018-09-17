package example.com.catdogapp.feature.home.dogs.presentation

import example.com.catdogapp.feature.home.dogs.domain.DogSource
import example.com.catdogapp.shared.ReactivePresenter
import example.com.catdogapp.shared.RxUtils
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class DogPresenter @Inject constructor(val source: DogSource): ReactivePresenter<DogView>() {

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
