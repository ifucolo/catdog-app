package example.com.catdogapp.feature.detail

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import example.com.catdogapp.R
import example.com.catdogapp.feature.home.tabs.cats.domain.entity.CatDetail
import example.com.catdogapp.feature.home.tabs.dogs.domain.entity.DogDetail
import example.com.catdogapp.utill.extensions.findByTag
import example.com.catdogapp.utill.extensions.hide
import kotlinx.android.synthetic.main.activity_cat_dog_detail.*
import java.io.Serializable

class DogDetailActivity : AppCompatActivity() {


    private val dogDetail by lazy { intent.getSerializableExtra(EXTRA_DOG) as DogDetail }

    companion object {

        const val EXTRA_DOG = "cat"

        fun launchIntent(context: Context, dogDetail: DogDetail): Intent {
            val intent = Intent(context, DogDetailActivity::class.java)

            intent.putExtra(EXTRA_DOG, dogDetail as Serializable)

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

        toolbar.title = dogDetail.name
        collapsingToolbarLayout.title = dogDetail.name

        toolbar.setNavigationOnClickListener {
            finish()
        }

        Glide.with(this)
                .load(dogDetail.imgUrl)
                .into(imgAnimal)
    }

    private fun setInfoData() {
        setTextOrHide(txtLifSpan, getString(R.string.life_span), dogDetail.lifeSpan)
        setTextOrHide(txtBreedFor, getString(R.string.breed_for), dogDetail.breedFor)
        setTextOrHide(txtBreedGroup, getString(R.string.breed_group), dogDetail.breedGroup)
        setTextOrHide(txtTemperament, getString(R.string.temperament), dogDetail.temperament)
        setTextOrHide(txtWeightImperial, getString(R.string.weight_imperial), dogDetail.weight.imperial)
        setTextOrHide(txtWeightMetric, getString(R.string.weight_metric), dogDetail.weight.imperial)
        setTextOrHide(txtHeightImperial, getString(R.string.height_imperial), dogDetail.height.imperial)
        setTextOrHide(txtHeightMetric, getString(R.string.height_metric), dogDetail.height.metric)
    }

    private fun setTextOrHide(txtView: TextView, tag: String, text: String) {
        val views = nestedScrollView.findByTag(tag)
        if (text.isEmpty())
            views.forEach { it.hide() }
        else
            txtView.text = text
    }
}
