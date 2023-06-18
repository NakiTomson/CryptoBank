package com.example.local.storage

import com.example.local.dao.UserDao
import com.example.local.entity.UserDb
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRoomStorage @Inject constructor(
    private val userDao: UserDao
) {

    fun getUserFlow(): Flow<UserDb?> = userDao.getUserFlow()

    suspend fun getUser(): UserDb? {
        return userDao.getUser()
    }

    suspend fun saveUser(userEntity: UserDb) {
        userDao.insertUser(userEntity)
    }

    suspend fun clearUser() {
        val user = getUser() ?: return
        return userDao.deleteUser(user)
    }
}