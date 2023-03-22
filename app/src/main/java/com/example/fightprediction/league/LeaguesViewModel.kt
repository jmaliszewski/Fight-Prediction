package com.example.fightprediction.league

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.fightprediction.repository.LeagueRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LeaguesViewModel @Inject constructor(
    private val leagueRepository: LeagueRepository
) : ViewModel() {

    val leagues = leagueRepository.getMyLeagues().asLiveData()
}