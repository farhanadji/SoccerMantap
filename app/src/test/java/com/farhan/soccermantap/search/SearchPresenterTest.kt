package com.farhan.soccermantap.search

import com.farhan.soccermantap.TestContextProvider
import com.farhan.soccermantap.model.Event
import com.farhan.soccermantap.model.SearchResponse
import com.farhan.soccermantap.network.ApiRepository
import com.farhan.soccermantap.presenter.SearchPresenter
import com.farhan.soccermantap.view.SearchView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SearchPresenterTest {
    @Mock
    private lateinit var view: SearchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: SearchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = SearchPresenter(view, gson, TestContextProvider())
    }

    @Test
    fun getSearchTest(){
        val events: MutableList<Event> = mutableListOf()
        val response = SearchResponse(events)
        val queryStr = "Arsenal"

        GlobalScope.launch {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson("", SearchResponse::class.java)
            ).thenReturn(response)

            presenter.getSearchData(queryStr)
            verify(view).showSearchResult(events)
        }
    }
}