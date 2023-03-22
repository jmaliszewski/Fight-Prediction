package com.example.fightprediction.repository

import com.example.fightprediction.dao.FighterDao
import com.example.fightprediction.database.entities.Fighter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FighterRepository @Inject constructor(private val fighterDao: FighterDao) : FighterDao {

    override suspend fun insertFighter(fighter: Fighter) {
        fighterDao.insertFighter(fighter)
    }

    override suspend fun insertFighters(fighters: List<Fighter>) {
        fighterDao.insertFighters(fighters)
    }

    override suspend fun updateFighter(fighter: Fighter) {
        fighterDao.updateFighter(fighter)
    }

    override suspend fun deleteFighters(fighters: List<Fighter>) {
        fighterDao.deleteFighters(fighters)
    }

    override fun findFighterById(id: Int): Fighter {
        return fighterDao.findFighterById(id)
    }

    override suspend fun deleteAllFighters() {
        fighterDao.deleteAllFighters()
    }
}