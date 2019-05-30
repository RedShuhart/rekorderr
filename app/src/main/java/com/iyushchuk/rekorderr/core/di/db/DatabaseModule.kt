package com.iyushchuk.rekorderr.core.di.db

import android.content.Context
import androidx.room.Room
import com.iyushchuk.rekorderr.core.domain.dao.RekordDao
import com.iyushchuk.rekorderr.core.domain.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabse(context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideRekordDao(appDataBase: AppDatabase): RekordDao {
        return appDataBase.rekordDao()
    }
}