package org.example.util;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        dropTables();
        createTables();
    }

    private void dropTables() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS users");
    }

    private void createTables() {
        jdbcTemplate.execute("CREATE TABLE users (id SERIAL PRIMARY KEY, login VARCHAR(255) UNIQUE," +
                " password VARCHAR(255), fio VARCHAR(255))");
    }
}

