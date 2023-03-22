package com.example.fightprediction.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fightprediction.database.models.EventWithFights
import com.example.fightprediction.models.FightDetails
import com.example.fightprediction.repository.EventRepository
import com.example.fightprediction.repository.FightRepository
import com.example.fightprediction.repository.FighterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val fightRepository: FightRepository,
    private val fighterRepository: FighterRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    val eventId = state.get<Int>("eventId") ?: 0

    val fightDetails = MutableLiveData<MutableList<FightDetails>>()

    init {
        val eventWithFights = eventRepository.loadEventWithFights(eventId)
        createFightsDetails(eventWithFights)
    }

    private fun createFightsDetails(eventWithFights: EventWithFights){
        val fightDetailsList: MutableList<FightDetails> = mutableListOf()
        for(fight in eventWithFights.fights){
            val firstFighter = fighterRepository.findFighterById(fight.firstFighterId)
            val secondFighter = fighterRepository.findFighterById(fight.secondFighterId)
            fightDetailsList.add(FightDetails(fight, firstFighter, secondFighter))
        }

        fightDetails.value = fightDetailsList;
    }

    sealed class EventDetailsEvents{
        data class NavigateToFightDetailsFragment(val eventId: Int) : EventDetailsEvents()
    }
}