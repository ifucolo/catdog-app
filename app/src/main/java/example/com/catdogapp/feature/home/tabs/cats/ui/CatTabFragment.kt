package example.com.catdogapp.feature.home.tabs.cats.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import example.com.catdogapp.R
import example.com.catdogapp.feature.detail.CatDetailActivity
import example.com.catdogapp.utill.extensions.hide
import example.com.catdogapp.utill.extensions.progressDialog
import example.com.catdogapp.utill.extensions.show
import example.com.catdogapp.feature.home.tabs.cats.domain.entity.CatDetail
import example.com.catdogapp.feature.home.tabs.cats.presentation.CatPresenter
import example.com.catdogapp.feature.home.tabs.cats.presentation.CatView
import example.com.catdogapp.shared.ui.BaseFragment
import example.com.catdogapp.utill.extensions.toast
import kotlinx.android.synthetic.main.fragment_tab.*
import org.koin.android.ext.android.inject

class CatTabFragment: BaseFragment(), CatView {

    private val presenter: CatPresenter by inject()
    private var progressDialog: ProgressDialog? = null

    companion object {
        fun newInstance(): CatTabFragment {
            return CatTabFragment()
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
        adapter = CatAdapter(cats) { catDetail ->
            if(catDetail.hasBreedInfo)
                startActivity(CatDetailActivity.launchIntent(context, catDetail))
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
        context?.toast(R.string.verify_connection)

        recyclerView.hide()
        btnTryAgain.show()
    }
}