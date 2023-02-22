package ru.barabanra.demokotlin.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.barabanra.demokotlin.dto.ClientResponseDto
import ru.barabanra.demokotlin.mapper.ClientMapper
import ru.barabanra.demokotlin.service.ClientService

@RestController
@RequestMapping("/api/v1")
class ClientsController(
    val clientMapper: ClientMapper,
    val clientService: ClientService,
) {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/client")
    fun find(@RequestParam id: Long): ClientResponseDto {
        val clientDto = clientService.findById(id)
        logger.debug("debug logging message hello")
        return clientMapper.map(clientDto)
    }

    @GetMapping("/clients")
    fun findAll(): List<ClientResponseDto> {
        val clientDto = clientService.findAll()
        return clientMapper.map(clientDto)
    }

}