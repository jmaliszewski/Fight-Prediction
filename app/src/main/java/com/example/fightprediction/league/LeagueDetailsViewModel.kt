package com.example.fightprediction.league

import androidx.lifecycle.ViewModel
import com.example.fightprediction.repository.LeagueRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LeagueDetailsViewModel @Inject constructor(
    private val leagueRepository: LeagueRepository
) : ViewModel() {
}