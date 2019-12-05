package com.farhan.soccermantap.view

import com.farhan.soccermantap.model.Event

interface SearchView {
    fun showSearchResult(search: List<Event>)
}