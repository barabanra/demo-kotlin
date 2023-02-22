package ru.barabanra.demokotlin.web.controller

import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import ru.barabanra.demokotlin.config.AbstractComponentsTest

internal class ClientsControllerTest : AbstractComponentsTest() {

    @Test
    fun findTest() {
        jdbcTemplate.execute("truncate clients")
        jdbcTemplate.execute("insert into clients (id, name, age) values (1, 'test', 24)")

        val response = getFile("/json/responses/saveClientsTest.json")

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients/1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(response, true))
    }

    @Test
    fun saveTest() {
        jdbcTemplate.execute("truncate clients")

        val response = getFile("/json/responses/saveClientsTest.json")

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getFile("/json/requests/saveClientsTest.json"))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(response, true))
    }
}