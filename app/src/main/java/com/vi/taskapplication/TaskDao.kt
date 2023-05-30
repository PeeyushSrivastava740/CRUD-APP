package com.vi.taskapplication

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Query("SELECT * from nameList")
    fun getall(): LiveData<List<TableData>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: TableData)

    @Query("SELECT * FROM nameList WHERE nameList.taskId == :id")
    fun get(id: Long): LiveData<TableData>

    @Update
    suspend fun update(items: TableData)

    @Delete
    suspend fun delete(items: TableData)

}