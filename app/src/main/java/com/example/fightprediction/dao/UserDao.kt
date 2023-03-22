package com.example.fightprediction.dao

import androidx.room.*
import com.example.fightprediction.database.entities.User
import com.example.fightprediction.database.entities.UserLeague

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("DELETE FROM User")
    suspend fun deleteUsers()

    @Query("SELECT * FROM User WHERE id = :id")
    suspend fun findUserById(id: Int) : User

    @Query("SELECT * FROM User")
    fun findUsers() : List<User>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUserLeagues(userLeagues: List<UserLeague>)

}