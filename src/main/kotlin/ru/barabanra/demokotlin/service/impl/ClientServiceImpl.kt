package ru.barabanra.demokotlin.service.impl

import org.springframework.stereotype.Service
import ru.barabanra.demokotlin.dto.ClientDto
import ru.barabanra.demokotlin.mapper.ClientMapper
import ru.barabanra.demokotlin.persistence.repository.ClientsRepository
import ru.barabanra.demokotlin.service.ClientService

@Service
class ClientServiceImpl(
    val clientsRepository: ClientsRepository,
    val clientMapper: ClientMapper
) : ClientService {

    override fun findById(id: Long): ClientDto {
        val clientEntity = clientsRepository.findById(id).orElse(null)
        return clientMapper.map(clientEntity)
    }

    override fun save(request: ClientDto): ClientDto {
        val clientEntity = clientMapper.toEntity(request)
        val saved = clientsRepository.save(clientEntity)
        return clientMapper.map(saved)
    }

}