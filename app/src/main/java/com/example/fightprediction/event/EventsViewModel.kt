package com.example.fightprediction.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fightprediction.common.Date
import com.example.fightprediction.database.entities.Event
import com.example.fightprediction.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val eventRepository: EventRepository,
) : ViewModel() {

    private val eventChannel = Channel<EventEvents>()
    val eventEvents = eventChannel.receiveAsFlow()

    private val eventFormatDate = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.ENGLISH)
    val events = eventRepository.getUpcomingEvents(Date.getCurrentDateWithFormatter(eventFormatDate)).asLiveData()


    fun onEventSelected(event: Event) = viewModelScope.launch {
        eventChannel.send(EventEvents.NavigateToEventDetailsFragment(event.id))
    }

    sealed class EventEvents{
        data class NavigateToEventDetailsFragment(val eventId: Int) : EventEvents()
    }
}