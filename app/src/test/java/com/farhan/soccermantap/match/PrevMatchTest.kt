package com.farhan.soccermantap.match

import com.farhan.soccermantap.TestContextProvider
import com.farhan.soccermantap.model.Event
import com.farhan.soccermantap.model.EventResponse
import com.farhan.soccermantap.network.ApiRepository
import com.farhan.soccermantap.presenter.EventPresenter
import com.farhan.soccermantap.view.EventView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PrevMatchTest {
    @Mock
    private lateinit var view: EventView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: EventPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = EventPresenter(view,gson, TestContextProvider())
    }

    @Test
    fun getPrevMatchTest(){
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val idLeague = "4328"

        GlobalScope.launch {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson("", EventResponse::class.java)
            ).thenReturn(response)

            presenter.getPrevMatch(idLeague)
            Mockito.verify(view).showEventData(events)
        }
    }
}