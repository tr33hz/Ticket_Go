package org.example.repository.mappers;

import org.example.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setId(rs.getLong("ID"));
        user.setLogin(rs.getString("LOGIN"));
        user.setPassword(rs.getString("PASSWORD"));
        user.setName(rs.getString("NAME"));

        return user;
    }
}
