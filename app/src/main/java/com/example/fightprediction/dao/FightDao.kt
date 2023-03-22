package com.example.fightprediction.dao

import androidx.room.*
import com.example.fightprediction.database.entities.Fight

@Dao
interface FightDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertFight(fight: Fight)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertFights(fights: List<Fight>)

    @Update
    suspend fun updateFight(fight: Fight)

    @Delete
    suspend fun deleteFights(fights: List<Fight>)

    @Query("DELETE FROM Fight")
    suspend fun deleteAllFights();

    @Query("SELECT * FROM Fight WHERE id = :id")
    fun findFightById(id: Int): Fight
}