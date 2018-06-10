package net.kakueki61.springscheduler

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate

@SpringBootApplication
class SpringSchedulerApplication {
    @Bean
    fun commandLineRunner(jdbcTemplate: JdbcTemplate) = CommandLineRunner {
        jdbcTemplate.execute("""CREATE TABLE IF NOT EXISTS accounts (
            id          BIGINT          PRIMARY KEY AUTO_INCREMENT,
            user_name   VARCHAR(100)    NOT NULL,
            password    VARCHAR(100)    NOT NULL,
            role        VARCHAR(8)      NOT NULL,
            enabled     BOOLEAN         NOT NULL    DEFAULT FALSE
        )""")
    }
}

fun main(args: Array<String>) {
    runApplication<SpringSchedulerApplication>(*args)
}
