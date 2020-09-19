package com.example.book_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {


    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView
    var previousmenuitem : MenuItem?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerlayout)
        coordinatorLayout = findViewById(R.id.coordinatorlayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.framelayout)
        navigationView = findViewById(R.id.navigationview)
        setUpToolbar()
        supportFragmentManager.beginTransaction().replace(R.id.framelayout, DashboardFragment()).addToBackStack("dashboard").commit()
        supportActionBar?.title = "Dashboard"
        navigationView.setCheckedItem(R.id.dashboard)

        var actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {

            if(previousmenuitem!=null){
                previousmenuitem?.isChecked = false
            }
            it.isCheckable = true
            it.isChecked = true
            previousmenuitem = it

            when(it.itemId){
                R.id.dashboard -> {
                    supportFragmentManager.beginTransaction().replace(R.id.framelayout, DashboardFragment()).addToBackStack("dashboard").commit()
                    supportActionBar?.title = "Dashboard"
                    drawerLayout.closeDrawers()
                }
                R.id.favourite ->{
                    supportFragmentManager.beginTransaction().replace(R.id.framelayout, FavouriteFragment()).addToBackStack("favourite").commit()
                    supportActionBar?.title = "Favourite"
                    drawerLayout.closeDrawers()
                }
                R.id.profile-> {
                    supportFragmentManager.beginTransaction().replace(R.id.framelayout, ProfileFragment()).addToBackStack("profile").commit()
                    supportActionBar?.title = "Profile"
                    drawerLayout.closeDrawers()
                }
                R.id.about ->{
                    supportFragmentManager.beginTransaction().replace(R.id.framelayout, AboutFragment()).addToBackStack("about").commit()
                    supportActionBar?.title = "About info"
                    drawerLayout.closeDrawers()
                }
            }
            return@setNavigationItemSelectedListener true
        }

    }

    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Toolbar title"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id =item.itemId
        if(id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)

    }

}