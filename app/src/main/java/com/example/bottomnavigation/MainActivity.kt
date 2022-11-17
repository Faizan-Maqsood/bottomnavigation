package com.example.bottomnavigation

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(){


   private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    private lateinit var bottom_nav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar

        drawerLayout = findViewById(R.id.drawerLayout)
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarToggle)

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        actionBarToggle.syncState()
        actionBarToggle.setToolbarNavigationClickListener {
            onBackPressed()
        }
        navView = findViewById(R.id.navView)
        bottom_nav = findViewById(R.id.navigation_bottom)

        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.hoome -> {
                    changeFragment(FirstFragment())
                 //   supportActionBar?.title = "Home"
                    true
                }
                R.id.person -> {
                    changeFragment(SecondFragment())
                  //  supportActionBar?.title = "Person"
                    true
                }
                R.id.setting -> {
                    changeFragment(ThirdFragment())
                 //   supportActionBar?.title = "Setting"
                    true
                }
                else -> {
                    false
                }
            }
        }

        navView.setNavigationItemSelectedListener { menuItem ->
            this.drawerLayout.closeDrawer(GravityCompat.START)
            when (menuItem.itemId) {
                R.id.hoome -> {
                    changeFragment(FirstFragment())
                    supportActionBar?.title = "Home"
                    true
                }
                R.id.person -> {
                    changeFragment(SecondFragment())
                    supportActionBar?.title = "Person"
                    true
                }
                R.id.setting -> {
                  changeFragment(ThirdFragment())
                    supportActionBar?.title = "Setting"
                    true
                }
                else -> {
                    false
                }
            }
        }

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(navView)
        return true
    }

     override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.END)
        } else {
            super.onBackPressed()
        }
    }

    fun changeFragment(frag : Fragment){
            val fragment = supportFragmentManager.beginTransaction()
            fragment.replace(R.id.frame, frag ).commit()
    }






    /* override fun onNavigationItemSelected(item: MenuItem): Boolean {
         when(item.itemId){
             R.id.home ->{
                 changeFragment(FirstFragment())
             }
             R.id.person ->{
                 changeFragment(SecondFragment())
             }
             R.id.setting ->{
                 changeFragment(ThirdFragment())
             }
         }
        return true
     }

     fun changeFragment(frag : Fragment){
         val fragment = supportFragmentManager.beginTransaction()
         fragment.replace(R.id.fragment_container, frag).commit()
     }*/

}

