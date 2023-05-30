package com.vi.taskapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vi.taskapplication.TableData
import com.vi.taskapplication.TaskDataRepository
import com.vi.taskapplication.TaskDatabase
import kotlinx.coroutines.launch


class DataRecordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskDataRepository
    val allItems: LiveData<List<TableData>>

    init {

        val dao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskDataRepository(dao)
        allItems = repository.allItems
    }

    fun insert(item: TableData) = viewModelScope.launch {
        repository.insert(item)
    }

    fun update(item: TableData) = viewModelScope.launch {
        repository.update(item)
    }

    fun delete(item: TableData) = viewModelScope.launch {
        repository.delete(item)
    }

    fun get(id: Long) = repository.get(id)
}