package example.com.catdogapp.feature.home.ui

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import example.com.catdogapp.R
import example.com.catdogapp.feature.home.tabs.cats.ui.CatTabFragment
import example.com.catdogapp.feature.home.tabs.dogs.ui.DogTabFragment

class HomePageAdapter constructor(val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> CatTabFragment.newInstance()
            1 -> DogTabFragment.newInstance()
            else -> CatTabFragment.newInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
       return when(position) {
            0 -> context.getString(R.string.cats)
            1 -> context.getString(R.string.dogs)
           else -> context.getString(R.string.cats)
       }
    }

    override fun getCount() = 2


}