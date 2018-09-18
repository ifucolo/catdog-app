package example.com.catdogapp.feature.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import example.com.catdogapp.R
import example.com.catdogapp.feature.home.ui.HomeActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(HomeActivity.launchIntent(this))
        }, 1500)
    }
}
