package com.farhan.soccermantap.view

import com.farhan.soccermantap.model.Event

interface EventView {
    fun showEventData(data: List<Event>)
}