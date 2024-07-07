package org.example.repository.mappers;

import org.example.model.Path;
import org.example.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PathRowMapper implements RowMapper<Path> {
    @Override
    public Path mapRow(ResultSet rs, int rowNum) throws SQLException {
        Path path = new Path();

        path.setStart(rs.getString("START"));
        path.setFinish(rs.getString("FINISH"));
        path.setTransporterId(rs.getLong("TRANSPORTER_ID"));
        path.setTravelTime(rs.getInt("TRAVEL_TIME"));

        return path;
    }
}
