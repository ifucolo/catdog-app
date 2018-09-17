package example.com.catdogapp.feature.home.dogs.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import example.com.catdogapp.R
import example.com.catdogapp.extensions.hide
import example.com.catdogapp.extensions.progressDialog
import example.com.catdogapp.extensions.show
import example.com.catdogapp.feature.home.dogs.domain.entity.DogDetail
import example.com.catdogapp.feature.home.dogs.presentation.DogPresenter
import example.com.catdogapp.feature.home.dogs.presentation.DogView
import example.com.catdogapp.shared.BaseFragment
import kotlinx.android.synthetic.main.fragment_tab.*
import javax.inject.Inject

class DogTabFragment: BaseFragment(), DogView {

    @Inject
    lateinit var presenter: DogPresenter

    var progressDialog: ProgressDialog? = null

    companion object {

        fun newInstance(): DogTabFragment {
            return DogTabFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab, container, false)

        getCoreComponent().inject(this)
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
        adapter = DogAdapter(dogs) {

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
        recyclerView.hide()
        btnTryAgain.show()
    }
}