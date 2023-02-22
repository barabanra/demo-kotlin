package ru.barabanra.demokotlin.mapper

import org.mapstruct.Mapper
import ru.barabanra.demokotlin.dto.ClientDto
import ru.barabanra.demokotlin.dto.ClientRequestDto
import ru.barabanra.demokotlin.dto.ClientResponseDto
import ru.barabanra.demokotlin.persistence.entity.ClientEntity

@Mapper
abstract class ClientMapper {

    abstract fun map(response: ClientDto): ClientResponseDto

    abstract fun map(response: List<ClientDto>): List<ClientResponseDto>

    abstract fun map(request: ClientRequestDto): ClientDto

    abstract fun toEntity(request: ClientDto): ClientEntity

    abstract fun map(saved: ClientEntity?): ClientDto

}