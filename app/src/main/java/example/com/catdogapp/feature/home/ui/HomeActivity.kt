package example.com.catdogapp.feature.home.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import example.com.catdogapp.R
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabs.setupWithViewPager(viewPager)
        viewPager.adapter = HomePageAdapter(this, supportFragmentManager)
    }
}
