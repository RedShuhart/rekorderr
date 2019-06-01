package com.iyushchuk.rekorderr.core.domain.repository

import com.iyushchuk.rekorderr.core.domain.dao.RekordDao
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import io.reactivex.Flowable
import javax.inject.Inject

class RekordRepository  @Inject internal constructor(
    private val rekordDao: RekordDao
) {

    fun getRekords(): Flowable<List<Rekord>> = rekordDao.getAll()

    fun saveRekord(rekord: Rekord) = rekordDao.insert(rekord)

    fun deleteRekord(rekord: Rekord) = rekordDao.delete(rekord)
}