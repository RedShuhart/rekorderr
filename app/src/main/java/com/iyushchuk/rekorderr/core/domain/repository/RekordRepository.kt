package com.iyushchuk.rekorderr.core.domain.repository

import com.iyushchuk.rekorderr.core.domain.dao.RekordDao
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import java.io.File
import javax.inject.Inject

class RekordRepository  @Inject internal constructor(
    private val rekordDao: RekordDao
) {

    fun getRekords(): Observable<List<Rekord>> = rekordDao.getAll()

    fun saveRekord(rekord: Rekord) = rekordDao.insert(rekord)

    fun deleteRekord(rekord: Rekord): Completable  {
        File(rekord.getPath()).delete()
        return rekordDao.delete(rekord)
    }
}