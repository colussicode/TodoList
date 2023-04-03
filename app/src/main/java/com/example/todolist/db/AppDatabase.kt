package com.example.todolist.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoModel::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDAO
}