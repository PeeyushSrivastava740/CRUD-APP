 package com.vi.taskapplication

import androidx.lifecycle.LiveData

class TaskDataRepository(private val datarecordDao: TaskDao) {

    val allItems: LiveData<List<TableData>> = datarecordDao.getall()

    fun get(id: Long):LiveData<TableData> {
        return datarecordDao.get(id)
    }

    suspend fun update(item: TableData) {
        datarecordDao.update(item)
    }

    suspend fun insert(item: TableData) {
        datarecordDao.insert(item)
    }

    suspend fun delete(item: TableData) {
        datarecordDao.delete(item)
    }
}