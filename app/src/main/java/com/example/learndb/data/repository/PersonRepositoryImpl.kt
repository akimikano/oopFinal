package com.example.learndb.data.repository
import com.example.learndb.data.Person
import com.example.learndb.data.dao.PersonDao
import com.example.learndb.data.db.PersonDatabase
import com.example.learndb.domain.datasource.PersonDataSource
import com.example.learndb.domain.repository.PersonRepository

class PersonRepositoryImpl(val dataSource: PersonDao)