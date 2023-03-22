package com.example.fightprediction.dao

import androidx.room.*
import com.example.fightprediction.database.models.EventWithFights
import com.example.fightprediction.database.entities.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertEvent(event: Event)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertEvents(events: List<Event>)

    @Update
    suspend fun updateEvent(event: Event)

    @Delete
    suspend fun deleteEvents(events: List<Event>)

    @Query("DELETE FROM Event")
    suspend fun deleteAllEvents()

    @Query("SELECT * FROM Event WHERE date >= :eventDate ORDER BY date DESC")
    fun getUpcomingEvents(eventDate: String): Flow<List<Event>>

    @Transaction
    @Query("SELECT * FROM Event WHERE id = :id")
    fun loadEventWithFights(id: Int): EventWithFights
}