package com.example.infolabsolution.bungokotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.match_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.uiThread
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity: AppCompatActivity() {
    private lateinit var match:MatchDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.match_detail)

        match = intent.getParcelableExtra("MATCH")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = match.eventName
        getData()

        swipe_match.onRefresh {
            getData()
        }
        swipe_match.setColorSchemeResources(R.color.colorAccent)
    }


    private fun getData() {
        swipe_match.isRefreshing = true
        doAsync {
            val matchdata = Gson().fromJson(TheSportDBApi(match.eventId).getmatchDetail(), ApiResponse::class.java)
            val homeTeam = Gson().fromJson(TheSportDBApi(match.homeTeamId).getTeamDetail(), ApiResponse::class.java)
            val awayTeam = Gson().fromJson(TheSportDBApi(match.awayTeamId).getTeamDetail(), ApiResponse::class.java)

            uiThread {
                val match = matchdata.events[0]
                val home = homeTeam.teams[0]
                val away = awayTeam.teams[0]

                val date = strToDate(match.eventDate)
                tv_date.text = changeFormatDate(date)

                Picasso.get().load(home.teamBadge).into(home_img)
                home_club.text = match.homeTeam
                home_score.text = match.homeScore
                home_formation.text = match.homeFormation
                home_goals.text = if(match.homeGoalDetails.isNullOrEmpty()) "-" else match.homeGoalDetails?.replace(";", ";\n")
                home_shots.text = match.homeShots ?: "-"
                home_goalkeeper.text = if(match.homeLineupGoalKeeper.isNullOrEmpty()) "-" else match.homeLineupGoalKeeper?.replace("; ", ";\n")
                home_defense.text = if(match.homeLineupGoalKeeper.isNullOrEmpty()) "-" else match.homeLineupGoalKeeper?.replace("; ", ";\n")
                home_midfield.text = if(match.homeLineupMidfield.isNullOrEmpty()) "-" else match.homeLineupMidfield?.replace("; ", ";\n")
                home_forward.text = if(match.homeLineupForward.isNullOrEmpty()) "-" else match.homeLineupForward?.replace("; ", ";\n")
                home_subtitutes.text = if(match.homeLineupSubstitutes.isNullOrEmpty()) "-" else match.homeLineupSubstitutes?.replace("; ", ";\n")

                Picasso.get().load(away.teamBadge).into(away_img)
                away_club.text = match.awayTeam
                away_score.text = match.awayScore
                away_formation.text = match.awayFormation
                away_goals.text = if(match.awayGoalsDetails.isNullOrEmpty()) "-" else match.awayGoalsDetails?.replace(";", ";\n")
                away_shots.text = match.awayShots ?: "-"
                away_goalkeeper.text = if(match.awayLineupGoalKeeper.isNullOrEmpty()) "-" else match.awayLineupGoalKeeper?.replace("; ", ";\n")
                away_defense.text = if(match.awayLineupDefense.isNullOrEmpty()) "-" else match.awayLineupDefense?.replace("; ", ";\n")
                away_midfield.text = if(match.awayLineupMidfield.isNullOrEmpty()) "-" else match.awayLineupMidfield?.replace("; ", ";\n")
                away_forward.text = if(match.awayLineupForward.isNullOrEmpty()) "-" else match.awayLineupForward?.replace("; ", ";\n")
                away_subtitutes.text = if(match.awayLineupSubstitutes.isNullOrEmpty()) "-" else match.awayLineupSubstitutes?.replace("; ", ";\n")

                swipe_match.isRefreshing = false
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if(item?.itemId == android.R.id.home){
            finish()
            true
        }else{
            super.onOptionsItemSelected(item)
        }
    }


}