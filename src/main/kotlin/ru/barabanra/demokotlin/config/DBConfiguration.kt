package ru.barabanra.demokotlin.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement
import ru.barabanra.demokotlin.dto.properties.DataSourceProperties
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class DBConfiguration {

    @Bean
    @ConfigurationProperties("db.datasource")
    fun dataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    fun dataSource(dataSourceProperties: DataSourceProperties?): DataSource {
        return HikariDataSource(dataSourceProperties)
    }
}