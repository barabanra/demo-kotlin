package ru.barabanra.demokotlin.service.impl

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import ru.barabanra.demokotlin.service.PojoWriter

@Component
class ObjectMapperPojoWriter(val objectMapper: ObjectMapper) : PojoWriter {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    override fun writeAsString(obj: Any): String? {
        return try {
            objectMapper.writeValueAsString(obj)
        } catch (ex: JsonProcessingException) {
            log.error("can't write pojo as string - {}", obj, ex)
            return null
        }
    }
}