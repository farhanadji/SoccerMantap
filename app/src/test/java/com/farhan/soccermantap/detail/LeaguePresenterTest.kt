package com.farhan.soccermantap.detail

import com.farhan.soccermantap.TestContextProvider
import com.farhan.soccermantap.model.League
import com.farhan.soccermantap.model.LeaguesResponse
import com.farhan.soccermantap.network.ApiRepository
import com.farhan.soccermantap.presenter.DetailLeaguePresenter
import com.farhan.soccermantap.view.DetailLeagueView
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

class LeaguePresenterTest {
    @Mock
    private lateinit var presenter: DetailLeaguePresenter

    @Mock
    private lateinit var view: DetailLeagueView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailLeaguePresenter(view, gson, TestContextProvider())
    }

    @Test
    fun getDetailLeague(){
        val leagues: MutableList<League> = mutableListOf()
        val response = LeaguesResponse(leagues)
        val idLeague = "4328"

        GlobalScope.launch {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson("", LeaguesResponse::class.java)
            ).thenReturn(response)

            presenter.getDetailLeague(idLeague)
            verify(view).showLeague(leagues)

        }
    }
}