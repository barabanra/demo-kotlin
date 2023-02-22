package ru.barabanra.demokotlin.persistence.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.barabanra.demokotlin.persistence.entity.ClientEntity

interface ClientsRepository : JpaRepository<ClientEntity, Long>