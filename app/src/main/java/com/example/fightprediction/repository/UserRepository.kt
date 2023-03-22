package com.example.fightprediction.repository

import com.example.fightprediction.api.ApiInterface
import com.example.fightprediction.api.requestModels.LoginModel
import com.example.fightprediction.api.requestModels.RegisterModel
import com.example.fightprediction.dao.UserDao
import com.example.fightprediction.database.entities.User
import com.example.fightprediction.database.entities.UserLeague
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val apiInterface: ApiInterface) : UserDao
{
    override suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    override suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    override suspend fun findUserById(id: Int): User {
        return userDao.findUserById(id)
    }

    override fun findUsers(): List<User> {
        return userDao.findUsers()
    }

    override suspend fun insertUserLeagues(userLeagues: List<UserLeague>) {
        userDao.insertUserLeagues(userLeagues)
    }

    override suspend fun deleteUsers() {
        userDao.deleteUsers()
    }

    suspend fun getDataForUser(loginModel: LoginModel) = apiInterface.login(loginModel)

    suspend fun register(registerModel: RegisterModel) = apiInterface.register(registerModel)

    suspend fun resetPassword(email: String) = apiInterface.resetPassword(email)

    suspend fun getData(authToken: String) = apiInterface.getData(authToken)
}