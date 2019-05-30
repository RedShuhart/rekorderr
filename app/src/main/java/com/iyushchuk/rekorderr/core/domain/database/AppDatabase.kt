package com.iyushchuk.rekorderr.core.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iyushchuk.rekorderr.core.domain.converters.AppConverters
import com.iyushchuk.rekorderr.core.domain.dao.RekordDao
import com.iyushchuk.rekorderr.core.domain.entities.Rekord

@Database(entities = [Rekord::class], version = AppDatabase.VERSION)
@TypeConverters(AppConverters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "rekorderr.db"
        const val VERSION = 1
    }
    abstract fun rekordDao(): RekordDao
}