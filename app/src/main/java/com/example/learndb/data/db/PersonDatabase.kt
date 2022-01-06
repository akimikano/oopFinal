package com.example.learndb.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.learndb.data.Person
import com.example.learndb.data.dao.PersonDao

object LAST_ID {
    var value = 0
}

@Database(entities = [Person::class], version=3)
abstract class PersonDatabase: RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {
        @Volatile
        private var INSTANCE: PersonDatabase? = null

        fun getInstance(context: Context): PersonDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PersonDatabase::class.java,
                        "person_database"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    LAST_ID.value = 0
                }
                return instance
            }
        }
    }
}