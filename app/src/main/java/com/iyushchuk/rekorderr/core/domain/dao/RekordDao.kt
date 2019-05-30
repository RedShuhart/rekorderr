package com.iyushchuk.rekorderr.core.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.iyushchuk.rekorderr.core.domain.entities.Rekord

@Dao
interface RekordDao {

    @Query("SELECT * FROM rekords")
    fun getAll(): List<Rekord>

    @Insert
    fun insert(rekords: Rekord)

    @Delete
    fun delete(rekord: Rekord)
}