package com.farhan.soccermantap.detail

import com.farhan.soccermantap.TestContextProvider
import com.farhan.soccermantap.model.*
import com.farhan.soccermantap.network.ApiRepository
import com.farhan.soccermantap.presenter.DetailEventPresenter
import com.farhan.soccermantap.presenter.DetailLeaguePresenter
import com.farhan.soccermantap.view.DetailLeagueView
import com.farhan.soccermantap.view.EventDetailView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DetailEventTest {
    @Mock
    private lateinit var presenter: DetailEventPresenter

    @Mock
    private lateinit var view: EventDetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailEventPresenter(view, gson, TestContextProvider())
    }

    @Test
    fun getDetailTeamTest(){
        val home: MutableList<Team> = mutableListOf()
        val away: MutableList<Team> = mutableListOf()

        val responseHome = TeamResponse(home)
        val responseAway = TeamResponse(away)
        val homeId = "133602"
        val awayId = "133614"

        GlobalScope.launch {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson("", TeamResponse::class.java)
            ).thenReturn(responseHome)

            Mockito.`when`(
                gson.fromJson("", TeamResponse::class.java)
            ).thenReturn(responseAway)

            presenter.getDetailTeam(homeId, awayId)

            verify(view).showTeam(home,away)
        }
    }
}