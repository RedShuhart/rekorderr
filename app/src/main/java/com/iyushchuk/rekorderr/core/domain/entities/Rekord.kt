package com.iyushchuk.rekorderr.core.domain.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import kotlin.reflect.full.isSubclassOf

@Entity(tableName = "rekords")
data class Rekord(
    @PrimaryKey var id: Long,

    @ColumnInfo(name = "type")
    val rekordType: RekordType,

    @ColumnInfo(name = "title")
    val title: String? = "",

    @ColumnInfo(name = "notes")
    val notes: String? = "",

    @ColumnInfo(name = "created_at")
    val createdAt: Instant = Instant.now()

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        RekordType.fromString(parcel.readString() ?: ""),
        parcel.readString(),
        parcel.readString(),
        Instant.ofEpochMilli(parcel.readLong())
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(rekordType.asString)
        parcel.writeString(title)
        parcel.writeString(notes)
        parcel.writeLong(createdAt.toEpochMilli())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rekord> {
        override fun createFromParcel(parcel: Parcel): Rekord {
            return Rekord(parcel)
        }

        override fun newArray(size: Int): Array<Rekord?> {
            return arrayOfNulls(size)
        }
    }

}

sealed class RekordType(val asString: String, val extension: String) {
    object VIDEO : RekordType("video", ".mp4")
    object AUDIO : RekordType("audio", ".m4a")
    object PHOTO : RekordType("photo", ".jpeg")

        companion object {
            private val map = RekordType::class.nestedClasses
                .filter { it.isSubclassOf(RekordType::class) }
                .map { it.objectInstance }
                .filterIsInstance<RekordType>()
                .associateBy { it.asString }

            fun fromString(type: String?) = requireNotNull(map[type]) {
                "No such constant ${RekordType::class.java.name}.$type"
            }

            val types = map.keys.toList()
            val values = map.values.toList()
        }
}
