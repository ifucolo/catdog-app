package example.com.catdogapp.feature.home.tabs.dogs.presentation

import example.com.catdogapp.feature.home.tabs.dogs.domain.entity.DogDetail

interface DogView {

    fun showLoading()
    fun hideLoading()

    fun showDogs(dogs: List<DogDetail>)
    fun showError()
}