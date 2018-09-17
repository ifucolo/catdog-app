package example.com.catdogapp.feature.home.cats.ui

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
import example.com.catdogapp.feature.home.cats.domain.entity.CatDetail
import example.com.catdogapp.feature.home.cats.presentation.CatPresenter
import example.com.catdogapp.feature.home.cats.presentation.CatView
import example.com.catdogapp.shared.BaseFragment
import kotlinx.android.synthetic.main.fragment_tab.*
import javax.inject.Inject

class CatTabFragment: BaseFragment(), CatView {

    @Inject
    lateinit var presenter: CatPresenter

    var progressDialog: ProgressDialog? = null

    companion object {

        fun newInstance(): CatTabFragment {
            return CatTabFragment()
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

        presenter.getCats()
    }

    override fun onDestroyView() {
        presenter.unbind()

        super.onDestroyView()
    }

    private fun bindListener() {
        btnTryAgain.setOnClickListener {
            presenter.getCats()
        }
    }

    private fun setupRecycler(cats: List<CatDetail>) = with(recyclerView) {
        val gridLayoutManager = GridLayoutManager(activity, 2)

        layoutManager = gridLayoutManager
        adapter = CatAdapter(cats) {

        }
    }

    override fun hideLoading() {
        if (progressDialog?.isShowing == true)
            progressDialog!!.dismiss()
    }

    override fun showLoading() {
        progressDialog = context?.progressDialog(R.string.loading)
    }

    override fun showCats(cats: List<CatDetail>) {
        recyclerView.show()
        btnTryAgain.hide()

        setupRecycler(cats)
    }

    override fun showError() {
        recyclerView.hide()
        btnTryAgain.show()
    }
}