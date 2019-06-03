package com.iyushchuk.rekorderr.core.di.util

import android.content.Context
import androidx.room.Room
import com.iyushchuk.rekorderr.core.domain.dao.RekordDao
import com.iyushchuk.rekorderr.core.domain.database.AppDatabase
import com.iyushchuk.rekorderr.core.domain.repository.RekordRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRekordDao(appDataBase: AppDatabase): RekordDao {
        return appDataBase.rekordDao()
    }

    @Provides
    @Singleton
    fun provideRekordRepository(rekordDao: RekordDao) = RekordRepository(rekordDao)
}