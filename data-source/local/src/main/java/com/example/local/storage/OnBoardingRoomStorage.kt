package com.example.local.storage

import com.example.local.dao.OnBoardingDao
import com.example.local.entity.OnBoardingDb
import javax.inject.Inject

class OnBoardingRoomStorage @Inject constructor(
    private val onBoardingDao: OnBoardingDao
) {
    suspend fun getOnBoardings(): List<OnBoardingDb>? {
        return onBoardingDao.getAll()
    }

    suspend fun saveOnBoarding(db: OnBoardingDb) {
        return onBoardingDao.insert(db)
    }

    suspend fun saveOnBoarding(db: List<OnBoardingDb>) {
        return onBoardingDao.insert(db)
    }
}