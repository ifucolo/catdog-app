package example.com.catdogapp.feature.detail

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import example.com.catdogapp.R
import example.com.catdogapp.feature.home.tabs.cats.domain.entity.CatDetail
import example.com.catdogapp.utill.extensions.findByTag
import example.com.catdogapp.utill.extensions.hide
import kotlinx.android.synthetic.main.activity_cat_dog_detail.*
import java.io.Serializable

class CatDetailActivity : AppCompatActivity() {

    private val catDetail by lazy { intent.getSerializableExtra(EXTRA_CAT) as CatDetail }

    companion object {

        const val EXTRA_CAT = "cat"

        fun launchIntent(context: Context, catDetail: CatDetail): Intent {
            val intent = Intent(context, CatDetailActivity::class.java)

            intent.putExtra(EXTRA_CAT, catDetail as Serializable)

            return intent
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_dog_detail)

        setHeader()
        setInfoData()
    }

    private fun setHeader() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.title = catDetail.name
        collapsingToolbarLayout.title = catDetail.name

        toolbar.setNavigationOnClickListener {
            finish()
        }

        Glide.with(this)
                .load(catDetail.imgUrl)
                .into(imgAnimal)
    }

    private fun setInfoData() {
        setTextOrHide(txtLifSpan, getString(R.string.life_span), catDetail.lifeSpan)
        setTextOrHide(txtBreedFor, getString(R.string.breed_for), catDetail.breedFor)
        setTextOrHide(txtBreedGroup, getString(R.string.breed_group), catDetail.breedGroup)
        setTextOrHide(txtTemperament, getString(R.string.temperament), catDetail.temperament)
        setTextOrHide(txtWeightImperial, getString(R.string.weight_imperial), catDetail.weight.imperial)
        setTextOrHide(txtWeightMetric, getString(R.string.weight_metric), catDetail.weight.imperial)
        setTextOrHide(txtHeightImperial, getString(R.string.height_imperial), catDetail.height.imperial)
        setTextOrHide(txtHeightMetric, getString(R.string.height_metric), catDetail.height.metric)
    }

    private fun setTextOrHide(txtView: TextView, tag: String, text: String) {
        val views = nestedScrollView.findByTag(tag)
        if (text.isEmpty())
            views.forEach { it.hide() }
        else
            txtView.text = text
    }
}
