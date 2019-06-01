package com.iyushchuk.rekorderr.core.di.db

import com.iyushchuk.rekorderr.core.domain.dao.RekordDao
import com.iyushchuk.rekorderr.core.domain.repository.RekordRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @DbScope
    @Provides
    @Singleton
    fun provideRekordRepository(rekordDao: RekordDao) = RekordRepository(rekordDao)
}
