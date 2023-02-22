package ru.barabanra.demokotlin.mapper

import org.mapstruct.Mapper
import ru.barabanra.demokotlin.dto.ClientDto
import ru.barabanra.demokotlin.dto.ClientResponseDto

@Mapper
abstract class ClientMapper {

    abstract fun map(response: ClientDto): ClientResponseDto

    abstract fun map(response: List<ClientDto>): List<ClientResponseDto>


}