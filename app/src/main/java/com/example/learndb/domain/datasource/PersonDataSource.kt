package com.example.learndb.domain.datasource

import com.example.learndb.data.Person

interface PersonDataSource {
    fun get(): List<Person>

    fun insert(person: Person): Boolean

    fun delete(person: Person): Boolean

    fun update(person: Person): Person
}