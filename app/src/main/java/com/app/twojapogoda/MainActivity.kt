package com.app.twojapogoda

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.app.twojapogoda.adapters.SlideFragmentPagerAdapter
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : AppCompatActivity() {

    lateinit var mainActivityViewModel: MainActivityViewModel
    var isFragmentMain:Int = 1
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.onLoadActivity()

        toolbar = findViewById(R.id.toolbar)
        val pagerAdapter = SlideFragmentPagerAdapter(supportFragmentManager)
        viewPager.adapter=pagerAdapter
        viewPager.currentItem = 1

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if(position==2){
                    tabanim_appbar.visibility=View.GONE
                    search_view.visibility=View.GONE
                }
                else{
                    tabanim_appbar.visibility=View.VISIBLE
                    search_view.visibility=View.VISIBLE
                }
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })

        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnSearchClickListener {
            if(viewPager.currentItem!=0){
                viewPager.currentItem = 0
                isFragmentMain=1
            }else{
                isFragmentMain=0
            }
        }
        MenuItemCompat.setOnActionExpandListener(
            searchItem,
            object : MenuItemCompat.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                    viewPager.currentItem=isFragmentMain
                    return true
                }
            })


        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mainActivityViewModel.setAndInsertCity(query.toString())
                toolbar.collapseActionView()
                viewPager.currentItem=1
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mainActivityViewModel.getCitiesYouWant(newText.toString())
                return true
            }
        }
        )
        return true
        }


}
