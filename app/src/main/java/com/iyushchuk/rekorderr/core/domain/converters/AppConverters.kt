package com.iyushchuk.rekorderr.core.domain.converters

import androidx.room.TypeConverter
import com.iyushchuk.rekorderr.core.domain.entities.RekordType
import java.time.Instant

class AppConverters {
    @TypeConverter
    fun fromInstant(instant: Instant?): Long? {
        return instant?.toEpochMilli()
    }

    @TypeConverter
    fun toInstant(value: Long?): Instant? {
        return value?.let { Instant.ofEpochMilli(it) }
    }

    @TypeConverter
    fun fromRekordType(rekordType: RekordType?): String? {
        return rekordType?.asString
    }
    @TypeConverter
    fun toRekordType(type: String?): RekordType? {
        return type?.let { RekordType.fromString(it) }
    }
}