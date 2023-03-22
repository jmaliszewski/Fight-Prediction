package com.example.fightprediction.dao

import androidx.room.*
import com.example.fightprediction.database.entities.EventLeague
import com.example.fightprediction.database.entities.League
import kotlinx.coroutines.flow.Flow

@Dao
interface LeagueDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertLeague(league: League)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertLeagues(leagues: List<League>)

    @Update
    suspend fun updateLeague(league: League)

    @Delete
    suspend fun deleteLeagues(leagues: List<League>)

    @Query("DELETE FROM League")
    suspend fun deleteAllLeagues()

    @Query("SELECT * FROM League")
    fun getMyLeagues() : Flow<List<League>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertLeagueEvents(leagueEvents: List<EventLeague>)
}