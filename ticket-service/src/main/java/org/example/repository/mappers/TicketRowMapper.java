package org.example.repository.mappers;

import org.example.model.Ticket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketRowMapper implements RowMapper<Ticket> {
    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ticket ticket = new Ticket();

        ticket.setId(rs.getLong("ID"));
        ticket.setPathId(rs.getLong("PATH_ID"));
        ticket.setDateTime(rs.getTimestamp("DATE_TIME").toLocalDateTime());
        ticket.setSeatNumber(rs.getInt("SEAT_NUMBER"));
        ticket.setPrice(rs.getBigDecimal("PRICE"));
        ticket.setAvailable(rs.getBoolean("AVAILABLE"));
        ticket.setUserId(rs.getLong("USER_ID"));


        return ticket;
    }
}
