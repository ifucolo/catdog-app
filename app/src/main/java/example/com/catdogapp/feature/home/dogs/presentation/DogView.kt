package example.com.catdogapp.feature.home.dogs.presentation

import example.com.catdogapp.feature.home.dogs.domain.entity.DogDetail

interface DogView {

    fun showLoading()
    fun hideLoading()

    fun showDogs(dogs: List<DogDetail>)
    fun showError()
}