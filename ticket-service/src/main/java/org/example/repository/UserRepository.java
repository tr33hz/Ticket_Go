package org.example.repository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.model.User;
import org.example.repository.mappers.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRepository {

    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Long registration(User user) {
        String query = "INSERT INTO users (login, password, fio) VALUES (:login, :password, :fio)";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("login", user.getLogin());
        mapSqlParameterSource.addValue("password", user.getPassword());
        mapSqlParameterSource.addValue("fio", user.getFio());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(query, mapSqlParameterSource, keyHolder, new String[]{"id"});

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public Optional<User> findById(Long id) {
        String query = "SELECT * FROM users WHERE ID = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(query, new UserRowMapper(), id));
    }

    public List<User> findAll() {

        String sql = "SELECT * FROM users";

        return jdbcTemplate.query(sql, new UserRowMapper());
    }
}


