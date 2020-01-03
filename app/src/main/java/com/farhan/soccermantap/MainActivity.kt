package com.farhan.soccermantap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.farhan.soccermantap.fragment.EventFragment
import com.farhan.soccermantap.fragment.favorite.FavoriteFragment
import com.farhan.soccermantap.fragment.LeagueFragment
import com.farhan.soccermantap.fragment.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private var savedInstanceState: Bundle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment(LeagueFragment())

        val buttonNav: BottomNavigationView = findViewById(R.id.navbar_view)
        buttonNav.setOnNavigationItemSelectedListener {
            item -> when(item.itemId) {
                R.id.nav_league -> {
                    val leagueFragment = LeagueFragment()
                    openFragment(leagueFragment)
                    return@setOnNavigationItemSelectedListener true
                }
               R.id.nav_search -> {
                   val searchFragment =
                       SearchFragment()
                   openFragment(searchFragment)
                   return@setOnNavigationItemSelectedListener true
               }
                R.id.nav_event -> {
                   val eventFragment = EventFragment()
                   openFragment(eventFragment)
                   return@setOnNavigationItemSelectedListener true
               }
                R.id.nav_favorite -> {
                    val favoriteFragment =
                        FavoriteFragment()
                    openFragment(favoriteFragment)
                    return@setOnNavigationItemSelectedListener  true
                }
               else -> false
            }
        }
    }

    private fun openFragment(fragment: Fragment){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, fragment, fragment.javaClass.simpleName)
                .commit()
        }
    }
}
