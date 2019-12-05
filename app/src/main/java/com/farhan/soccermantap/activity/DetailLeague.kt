package com.farhan.soccermantap.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.farhan.soccermantap.R
import com.farhan.soccermantap.model.League
import kotlinx.android.synthetic.main.activity_detail_league.*

class DetailLeague : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)
        val league =  intent.extras?.getParcelable<League>("item")

        leagueNameDetail.text = league?.name
        leagueDescDetail.text = league?.description

        Glide.with(this).load(league?.badge).into(leagueBadgeDetail)
    }
}
