package com.example.learndb.presentation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.learndb.data.Person
import com.example.learndb.data.db.LAST_ID
import com.example.learndb.data.db.PersonDatabase
import kotlin.random.Random

class MainViewModel(val db: PersonDatabase): ViewModel() {
    var name: String = ""
    var prof: String = ""
    val people = db.personDao().getAll()

    fun onClickDelete(person: Person) {
        db.personDao().delete(person)
    }

    fun onClickAdd() {
        db.personDao().add(Person(++LAST_ID.value, name, prof))
    }
}

class MainViewModelFactory(val db: PersonDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(db) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}