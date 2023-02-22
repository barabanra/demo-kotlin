package ru.barabanra.demokotlin.persistence.entity

import javax.persistence.*

@Entity
@Table(name = "clients")
data class ClientEntity(

    val name: String?,

    val age: Long?,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
)
