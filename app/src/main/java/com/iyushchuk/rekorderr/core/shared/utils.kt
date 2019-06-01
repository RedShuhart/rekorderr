package com.iyushchuk.rekorderr.core.shared

import android.os.Environment
import com.iyushchuk.rekorderr.core.domain.entities.RekordType

fun getFileDirectory(type: RekordType): String
        = Environment.getExternalStoragePublicDirectory(type.directory).absolutePath
