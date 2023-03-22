package com.example.fightprediction.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fightprediction.dao.*
import com.example.fightprediction.database.entities.*

@Database(entities = [Bet::class, Event::class, EventLeague::class, Fight::class, Fighter::class, League::class,
    User::class, UserLeague:: class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun betDao(): BetDao
    abstract fun eventDao(): EventDao
    abstract fun fightDao(): FightDao
    abstract fun fighterDao(): FighterDao
    abstract fun leagueDao(): LeagueDao
    abstract fun userDao(): UserDao
}