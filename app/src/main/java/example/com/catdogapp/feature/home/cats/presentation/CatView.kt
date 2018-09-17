package example.com.catdogapp.feature.home.cats.presentation

import example.com.catdogapp.feature.home.cats.domain.entity.CatDetail

interface CatView {

    fun showLoading()
    fun hideLoading()

    fun showCats(cats: List<CatDetail>)
    fun showError()
}