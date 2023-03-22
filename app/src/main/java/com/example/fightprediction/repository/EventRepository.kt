package com.example.fightprediction.repository

import com.example.fightprediction.dao.EventDao
import com.example.fightprediction.database.models.EventWithFights
import com.example.fightprediction.database.entities.Event
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepository @Inject constructor(private val eventDao: EventDao) : EventDao {

    override suspend fun insertEvent(event: Event) {
        eventDao.insertEvent(event)
    }

    override suspend fun insertEvents(events: List<Event>) {
        eventDao.insertEvents(events)
    }

    override suspend fun updateEvent(event: Event) {
        eventDao.updateEvent(event)
    }

    override suspend fun deleteEvents(events: List<Event>) {
        eventDao.deleteEvents(events)
    }

    override fun getUpcomingEvents(eventDate: String): Flow<List<Event>> {
        return eventDao.getUpcomingEvents(eventDate)
    }

    override fun loadEventWithFights(id: Int): EventWithFights {
        return eventDao.loadEventWithFights(id)
    }

    override suspend fun deleteAllEvents() {
        eventDao.deleteAllEvents()
    }
}