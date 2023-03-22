package com.example.fightprediction.dao

import androidx.room.*
import com.example.fightprediction.database.entities.Bet

@Dao
interface BetDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertBets(bets: List<Bet>)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertBet(bet: Bet)

    @Delete
    suspend fun deleteBets(bets: List<Bet>)

    @Query("DELETE FROM Bet")
    suspend fun deleteAllBets()
}