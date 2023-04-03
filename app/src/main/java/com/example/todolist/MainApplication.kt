package com.example.todolist

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.todolist.db.AppDatabase

private const val DB_NAME = "tododb"

class MainApplication : Application() {

    val databaseInstance: AppDatabase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        buildDatabase(this)
    }

    private fun buildDatabase(context: Context) : AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
    }
}