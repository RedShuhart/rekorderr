package com.iyushchuk.rekorderr.core.domain.entities

import android.os.Environment
import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iyushchuk.rekorderr.core.shared.getFileDirectory
import java.time.Instant
import java.util.*
import kotlin.reflect.full.isSubclassOf

@Entity(tableName = "rekords")
data class Rekord(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "type")
    val rekordType: RekordType,

    @ColumnInfo(name = "title")
    val title: String? = "",

    @ColumnInfo(name = "notes")
    val notes: String? = "",

    @ColumnInfo(name = "created_at")
    val createdAt: Instant = Instant.now(),

    @ColumnInfo(name = "length")
    val length: Long? = null

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        RekordType.fromString(parcel.readString() ?: ""),
        parcel.readString(),
        parcel.readString(),
        Instant.ofEpochMilli(parcel.readLong()),
        parcel.readLong().let { if (it == 0L ) null else it }
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(rekordType.asString)
        parcel.writeString(title)
        parcel.writeString(notes)
        parcel.writeLong(createdAt.toEpochMilli())
        parcel.writeLong(length ?: 0L)
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

    fun getPath() = "${getFileDirectory(rekordType)}/$id${rekordType.extension}"

}

sealed class RekordType(val asString: String, val extension: String, val directory: String) {
    object VIDEO : RekordType("video", ".mp4", Environment.DIRECTORY_MOVIES)
    object AUDIO : RekordType("audio", ".m4a", Environment.DIRECTORY_MUSIC)
    object PHOTO : RekordType("photo", ".jpeg", Environment.DIRECTORY_PICTURES)

    companion object {

    fun fromString(type: String) = when (type) {
            VIDEO.asString -> VIDEO
            AUDIO.asString -> AUDIO
            PHOTO.asString -> PHOTO
            else -> throw  Exception("No such constant ${RekordType::class.java.name}.$type")
        }
    }
}
