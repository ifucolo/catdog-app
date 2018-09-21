package example.com.catdogapp.feature.home.tabs.dogs.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import example.com.catdogapp.R
import example.com.catdogapp.feature.detail.DogDetailActivity
import example.com.catdogapp.utill.extensions.hide
import example.com.catdogapp.utill.extensions.progressDialog
import example.com.catdogapp.utill.extensions.show
import example.com.catdogapp.feature.home.tabs.dogs.domain.entity.DogDetail
import example.com.catdogapp.feature.home.tabs.dogs.presentation.DogPresenter
import example.com.catdogapp.feature.home.tabs.dogs.presentation.DogView
import example.com.catdogapp.shared.ui.BaseFragment
import example.com.catdogapp.utill.extensions.toast
import kotlinx.android.synthetic.main.fragment_tab.*
import org.koin.android.ext.android.inject

class DogTabFragment: BaseFragment(), DogView {

    private val presenter: DogPresenter by inject()
    private var progressDialog: ProgressDialog? = null

    companion object {
        fun newInstance(): DogTabFragment {
            return DogTabFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab, container, false)

        presenter.bind(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindListener()

        presenter.getDogs()
    }

    override fun onDestroyView() {
        presenter.unbind()

        super.onDestroyView()
    }

    private fun bindListener() {
        btnTryAgain.setOnClickListener {
            presenter.getDogs()
        }
    }

    private fun setupRecycler(dogs: List<DogDetail>) = with(recyclerView) {
        val gridLayoutManager = GridLayoutManager(activity, 2)

        layoutManager = gridLayoutManager
        adapter = DogAdapter(dogs) {dogDetail ->
            if (dogDetail.hasBreedInfo)
                startActivity(DogDetailActivity.launchIntent(context, dogDetail))
        }
    }

    override fun hideLoading() {
        if (progressDialog?.isShowing == true)
            progressDialog!!.dismiss()
    }

    override fun showLoading() {
        progressDialog = context?.progressDialog(R.string.loading)
    }

    override fun showDogs(dogs: List<DogDetail>) {
        recyclerView.show()
        btnTryAgain.hide()

        setupRecycler(dogs)
    }

    override fun showError() {
        context?.toast(R.string.verify_connection)

        recyclerView.hide()
        btnTryAgain.show()
    }
}