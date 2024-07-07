package org.example.repository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.model.Path;
import org.example.model.User;
import org.example.repository.mappers.PathRowMapper;
import org.example.repository.mappers.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PathRepository {

    JdbcTemplate jdbcTemplate;

    public Optional<Path> findById(Long id) {
        String query = "SELECT * FROM paths WHERE ID = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(query, new PathRowMapper(), id));
    }

}
