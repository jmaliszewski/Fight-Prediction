package com.example.fightprediction.repository

import com.example.fightprediction.dao.LeagueDao
import com.example.fightprediction.database.entities.EventLeague
import com.example.fightprediction.database.entities.League
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeagueRepository @Inject constructor(private val leagueDao: LeagueDao) : LeagueDao {

    override suspend fun insertLeague(league: League) {
        leagueDao.insertLeague(league)
    }

    override suspend fun insertLeagues(leagues: List<League>) {
        leagueDao.insertLeagues(leagues)
    }

    override suspend fun updateLeague(league: League) {
        leagueDao.updateLeague(league)
    }

    override suspend fun deleteLeagues(leagues: List<League>) {
        leagueDao.deleteLeagues(leagues)
    }

    override suspend fun insertLeagueEvents(leagueEvents: List<EventLeague>) {
        leagueDao.insertLeagueEvents(leagueEvents)
    }

    override fun getMyLeagues(): Flow<List<League>> {
        return leagueDao.getMyLeagues()
    }

    override suspend fun deleteAllLeagues() {
        leagueDao.deleteAllLeagues()
    }
}