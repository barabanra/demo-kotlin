package ru.barabanra.demokotlin.web.controller

import org.springframework.web.bind.annotation.*
import ru.barabanra.demokotlin.dto.ClientRequestDto
import ru.barabanra.demokotlin.dto.ClientResponseDto
import ru.barabanra.demokotlin.mapper.ClientMapper
import ru.barabanra.demokotlin.service.ClientService

@RestController
@RequestMapping("/api/v1/clients")
class ClientsController(
    val clientMapper: ClientMapper,
    val clientService: ClientService,
) {

    @GetMapping("/{clientId}")
    fun find(@PathVariable clientId: Long): ClientResponseDto {
        val clientDto = clientService.findById(clientId)
        return clientMapper.map(clientDto)
    }

    @PostMapping
    fun save(@RequestBody request: ClientRequestDto): ClientResponseDto {
        val clientDto = clientMapper.map(request)
        val saved = clientService.save(clientDto)
        return clientMapper.map(saved)
    }

}