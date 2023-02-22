package ru.barabanra.demokotlin.service.impl

import org.springframework.stereotype.Service
import ru.barabanra.demokotlin.dto.ClientDto
import ru.barabanra.demokotlin.service.ClientService

@Service
class ClientServiceImpl : ClientService {

    override fun findById(id: Long): ClientDto {
        return ClientDto("test", 25)
    }

    override fun findAll(): List<ClientDto> {
        return listOf(ClientDto("test", 25), ClientDto("тест", 26))
    }

}