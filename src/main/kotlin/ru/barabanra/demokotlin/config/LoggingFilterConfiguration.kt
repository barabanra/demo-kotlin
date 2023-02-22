package ru.barabanra.demokotlin.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import ru.barabanra.demokotlin.web.filters.LoggingFilter

@Configuration
class LoggingFilterConfiguration {

    @Bean
    fun requestResponseLoggingFilterRegistration(filter: LoggingFilter): FilterRegistrationBean<LoggingFilter>? {
        val filterRegistration: FilterRegistrationBean<LoggingFilter> = FilterRegistrationBean<LoggingFilter>()
        filterRegistration.filter = filter
        filterRegistration.urlPatterns = listOf("/*")
        filterRegistration.order = Ordered.LOWEST_PRECEDENCE - 2
        return filterRegistration
    }

}