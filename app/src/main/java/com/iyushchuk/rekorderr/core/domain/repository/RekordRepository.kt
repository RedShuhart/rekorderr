package com.iyushchuk.rekorderr.core.domain.repository

import com.iyushchuk.rekorderr.core.domain.dao.RekordDao
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import io.reactivex.Flowable
import io.reactivex.Observable
import java.io.File
import javax.inject.Inject

class RekordRepository  @Inject internal constructor(
    private val rekordDao: RekordDao
) {

    fun getRekords(): Observable<List<Rekord>> = rekordDao.getAll()

    fun saveRekord(rekord: Rekord): Rekord  {
        rekordDao.insert(rekord)
        return rekord
    }

    fun deleteRekord(rekord: Rekord)  {
        File(rekord.getPath()).delete()
        rekordDao.delete(rekord)
    }
}