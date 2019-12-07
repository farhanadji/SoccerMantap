package com.farhan.soccermantap.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.farhan.soccermantap.model.Favorite
import org.jetbrains.anko.db.*

class FavoriteHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null,1){
    companion object {
        private var instance: FavoriteHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): FavoriteHelper {
            if(instance == null){
                instance = FavoriteHelper(ctx.applicationContext)
            }
            return instance as FavoriteHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            Favorite.TABLE_FAVORITE, true,
            Favorite.EVENT_ID to TEXT + PRIMARY_KEY,
            Favorite.EVENT_NAME to TEXT,
            Favorite.EVENT_DATE to TEXT,
            Favorite.HOME_TEAM_ID to TEXT,
            Favorite.HOME_TEAM_NAME to TEXT,
            Favorite.HOME_TEAM_SCORE to TEXT,
            Favorite.HOME_TEAM_GOAL to TEXT,
            Favorite.HOME_TEAM_GOALKEEPER to TEXT,
            Favorite.HOME_TEAM_DEFENSE to TEXT,
            Favorite.HOME_TEAM_MIDFIELD to TEXT,
            Favorite.HOME_TEAM_FORWARD to TEXT,
            Favorite.HOME_TEAM_SUBSTITUTES to TEXT,
            Favorite.AWAY_TEAM_ID to TEXT,
            Favorite.AWAY_TEAM_NAME to TEXT,
            Favorite.AWAY_TEAM_SCORE to TEXT,
            Favorite.AWAY_TEAM_GOAL to TEXT,
            Favorite.AWAY_TEAM_GOALKEEPER to TEXT,
            Favorite.AWAY_TEAM_DEFENSE to TEXT,
            Favorite.AWAY_TEAM_MIDFIELD to TEXT,
            Favorite.AWAY_TEAM_FORWARD to TEXT,
            Favorite.AWAY_TEAM_SUBSTITUTES to TEXT,
            Favorite.SPORT_TYPE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.dropTable("TABLE_FAVORITE", true)
    }
}

val Context.database: FavoriteHelper
    get() = FavoriteHelper.getInstance(applicationContext)
