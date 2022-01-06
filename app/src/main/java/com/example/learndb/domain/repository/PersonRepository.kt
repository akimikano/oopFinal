package com.example.learndb.domain.repository
import com.example.learndb.data.Person

interface PersonRepository {
    fun addPerson(person: Person)

    fun getPeople()

    fun deletePerson(person: Person): Boolean

    fun updatePerson(person: Person): Boolean
}