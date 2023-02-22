package ru.barabanra.demokotlin.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringDocConfiguration {

    @Bean
    fun openAPI(
        @Value("\${info.app.version}") appVersion: String?,
        @Value("\${springdoc.servers}") server: String?
    ): OpenAPI? {
        return OpenAPI()
            .addServersItem(Server().url(server))
            .info(
                Info().title("Core API")
                    .description("Galileo application Core API")
                    .version(appVersion)
                    .license(License().name("Apache 2.0").url("http://springdoc.org"))
            )
            .components(
                Components()
                    .addSecuritySchemes(
                        "basicScheme",
                        SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")
                    )
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("SpringShop Wiki Documentation")
                    .url("https://springshop.wiki.github.org/docs")
            )
    }

}