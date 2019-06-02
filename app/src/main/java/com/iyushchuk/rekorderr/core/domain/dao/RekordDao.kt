package com.iyushchuk.rekorderr.core.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import io.reactivex.Flowable
import io.reactivex.Observable

@Dao
interface RekordDao {

    @Query("SELECT * FROM rekords")
    fun getAll(): Observable<List<Rekord>>

    @Insert
    fun insert(rekord: Rekord)

    @Delete
    fun delete(rekord: Rekord)
}