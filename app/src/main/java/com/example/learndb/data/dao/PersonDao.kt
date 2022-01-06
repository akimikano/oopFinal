package com.example.learndb.data.dao
import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.learndb.data.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM person ORDER BY -id")
    fun getAll(): LiveData<List<Person>>

    @Insert
    fun add(vararg person: Person)

    @Delete
    fun delete(person: Person)

    @Update
    fun update(person: Person)
}