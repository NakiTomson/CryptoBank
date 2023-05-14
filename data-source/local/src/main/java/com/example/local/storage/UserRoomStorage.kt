package com.example.local.storage

import com.example.entity.UserEntity
import com.example.local.dao.UserDao
import com.example.local.entity.UserDb
import javax.inject.Inject

class UserRoomStorage @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun saveUser(userEntity: UserDb) {
        userDao.insert(userEntity)
    }

    suspend fun getUser(): UserDb? {
        return userDao.get()
    }

    suspend fun clearUser() {
        val user = getUser() ?: return
        return userDao.delete(user)
    }
}