package com.vi.taskapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nameList")
data class TableData(
    @PrimaryKey(autoGenerate = true)
    val taskId: Long,
    @ColumnInfo
    val taskName: String,

    )
