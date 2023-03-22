package com.example.fightprediction.repository

import com.example.fightprediction.dao.FightDao
import com.example.fightprediction.database.entities.Fight
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FightRepository @Inject constructor(private val fightDao: FightDao) : FightDao {

    override suspend fun insertFight(fight: Fight) {
        fightDao.insertFight(fight)
    }

    override suspend fun insertFights(fights: List<Fight>) {
        fightDao.insertFights(fights)
    }

    override suspend fun updateFight(fight: Fight) {
        fightDao.updateFight(fight)
    }

    override suspend fun deleteFights(fights: List<Fight>) {
        fightDao.deleteFights(fights)
    }

    override fun findFightById(id: Int): Fight {
        return fightDao.findFightById(id)
    }

    override suspend fun deleteAllFights() {
        fightDao.deleteAllFights()
    }
}