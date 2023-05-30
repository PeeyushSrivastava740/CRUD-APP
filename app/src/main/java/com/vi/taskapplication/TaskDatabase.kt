package com.vi.taskapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TableData::class],
    version = 1,
    exportSchema = false
)
abstract class TaskDatabase:RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance as TaskDatabase
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "datarecords_database"
                ).fallbackToDestructiveMigration()          // TODO: migration
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}