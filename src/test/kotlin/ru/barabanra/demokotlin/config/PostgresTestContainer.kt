package ru.barabanra.demokotlin.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.testcontainers.containers.PostgreSQLContainer

class PostgresTestContainer: PostgreSQLContainer<PostgresTestContainer>("postgres:13.3") {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    override fun start() {
        super.start()
        System.setProperty("POSTGRES_TEST_CONTAINER_URL", jdbcUrl)
        System.setProperty("POSTGRES_TEST_CONTAINER_USERNAME", username)
        System.setProperty("POSTGRES_TEST_CONTAINER_PASSWORD", password)
        log.debug(
            "[postgres] POSTGRES_TEST_CONTAINER_URL - {}," +
                    " POSTGRES_TEST_CONTAINER_USERNAME - {}," +
                    " POSTGRES_TEST_CONTAINER_PASSWORD - {}",
            jdbcUrl, username, password
        )
    }

    override fun stop() {
        //do nothing, JVM handles shut down
    }
}