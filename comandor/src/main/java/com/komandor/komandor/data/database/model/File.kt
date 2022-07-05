package com.komandor.komandor.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FILES")

data class File(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name = "type")
    var type: Int = 0,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "name_sign")
    var nameSign: String? = null,

    @ColumnInfo(name = "file_id")
    var fileID: Long? = null,

    @ColumnInfo(name = "cms")
    var cms: String? = null,

    @ColumnInfo(name = "file_path")
    var filePath: String? = null
) {
}