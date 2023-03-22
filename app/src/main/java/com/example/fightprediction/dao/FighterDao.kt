package com.example.fightprediction.dao

import androidx.room.*
import com.example.fightprediction.database.entities.Fighter

@Dao
interface FighterDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertFighter(fighter: Fighter)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertFighters(fighters: List<Fighter>)

    @Update
    suspend fun updateFighter(fighter: Fighter)

    @Delete
    suspend fun deleteFighters(fighters: List<Fighter>)

    @Query("DELETE FROM Fighter")
    suspend fun deleteAllFighters()

    @Query("SELECT * FROM Fighter WHERE id = :id")
    fun findFighterById(id: Int): Fighter
}