package example.com.catdogapp.feature

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import example.com.catdogapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabs.setupWithViewPager(viewPager)
        viewPager.adapter =  MainPageAdapter(this, supportFragmentManager)
    }
}
