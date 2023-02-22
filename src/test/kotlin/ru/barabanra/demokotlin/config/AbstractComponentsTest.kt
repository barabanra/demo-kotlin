package ru.barabanra.demokotlin.config

import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.web.servlet.MockMvc
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.nio.charset.StandardCharsets
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers(disabledWithoutDocker = true)
abstract class AbstractComponentsTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    companion object {
        @Container
        val container = PostgresTestContainer()
    }

    fun execute(sql: String) {
        jdbcTemplate.execute(sql)
    }

    fun getFile(filePath: String): String {
        return IOUtils.toString(
            Objects.requireNonNull(this.javaClass.getResourceAsStream(filePath)),
            StandardCharsets.UTF_8
        )
    }
}