package com.example.learndb.domain.usecase
import com.example.learndb.data.dao.PersonDao
import com.example.learndb.domain.repository.PersonRepository

class GetPeopleUseCase(private val dao: PersonDao) {
    fun execute() = dao.getAll()
}

