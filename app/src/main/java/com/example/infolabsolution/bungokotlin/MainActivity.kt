package com.example.infolabsolution.bungokotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {
    private  var leagueId = "4328" //EPL
    private var fixture = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_button.setOnNavigationItemSelectedListener {
                item -> when(item.itemId){
            R.id.navigation_prev -> {
                fixture = 1
                openFragment(MatchFragment.newInstance(fixture, leagueId))
                return@setOnNavigationItemSelectedListener true
            }
            R.id.navigation_next -> {
                fixture = 2
                openFragment(MatchFragment.newInstance(fixture, leagueId))
                return@setOnNavigationItemSelectedListener true
            }
        }
            false
        }
        openFragment(MatchFragment.newInstance(fixture,leagueId))
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.league, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menu1 -> {
                leagueId = "4328"
            }
            R.id.menu2 -> {
                leagueId ="4331"
            }
            R.id.menu3 -> {
                leagueId="4332"
            }
            R.id.menu4 -> {
                leagueId="4334"
            }
            R.id.menu5 -> {
                leagueId = "4335"
            }
        }
        openFragment(MatchFragment.newInstance(fixture, leagueId))
        return super.onOptionsItemSelected(item)
    }
}
