package com.example.fightprediction.repository

import com.example.fightprediction.dao.BetDao
import com.example.fightprediction.database.entities.Bet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BetRepository @Inject constructor(private val betDao: BetDao) : BetDao {

    override suspend fun insertBets(bets: List<Bet>) {
        betDao.insertBets(bets)
    }

    override suspend fun insertBet(bet: Bet) {
        betDao.insertBet(bet)
    }

    override suspend fun deleteBets(bets: List<Bet>) {
        betDao.deleteBets(bets)
    }

    override suspend fun deleteAllBets() {
        betDao.deleteAllBets()
    }
}