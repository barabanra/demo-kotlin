package ru.barabanra.demokotlin.service

import ru.barabanra.demokotlin.dto.ClientDto

interface ClientService {

    fun findById(id: Long): ClientDto

    fun save(request: ClientDto): ClientDto

}