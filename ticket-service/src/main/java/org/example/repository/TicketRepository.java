package org.example.repository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.model.Ticket;
import org.example.repository.mappers.TicketRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TicketRepository {

    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Long save(Ticket ticket) {

        String query = "INSERT INTO tickets (path_id, date_time, seat_number, price, available, user_id) " +
                "VALUES (:path_id, :date_time, :seat_number, :price, :available, :user_id)";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("path_id", ticket.getPathId());
        mapSqlParameterSource.addValue("date_time", ticket.getDateTime());
        mapSqlParameterSource.addValue("seat_number", ticket.getSeatNumber());
        mapSqlParameterSource.addValue("price", ticket.getPrice());
        mapSqlParameterSource.addValue("available", ticket.getAvailable());
        mapSqlParameterSource.addValue("user_id", ticket.getUserId());


        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(query, mapSqlParameterSource, keyHolder, new String[]{"id"});

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public Optional<Ticket> findById(Long id) {
        String query = "SELECT * FROM tickets WHERE ID = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(query, new TicketRowMapper(), id));
    }

    public List<Ticket> findAvailableTickets(String dateTime, String start, String finish, String transporterName, int page, int size) {
        StringBuilder sql = new StringBuilder("SELECT t.* FROM tickets t " +
                "JOIN paths p ON t.path_id = p.id " +
                "JOIN transporters tr ON p.transporter_id = tr.id " +
                "WHERE t.available = true ");

        if (StringUtils.hasText(dateTime)) {
            sql.append(" AND t.date_time = '").append(dateTime).append("'");
        }
        if (StringUtils.hasText(start)) {
            sql.append(" AND p.start LIKE '%").append(start).append("%'");
        }
        if (StringUtils.hasText(finish)) {
            sql.append(" AND p.finish LIKE '%").append(finish).append("%'");
        }
        if (StringUtils.hasText(transporterName)) {
            sql.append(" AND tr.name LIKE '%").append(transporterName).append("%'");
        }

        sql.append(" LIMIT ").append(size).append(" OFFSET ").append(page * size);

        return jdbcTemplate.query(sql.toString(), new TicketRowMapper());
    }

    public void purchaseTicket(Long ticketId, Long userId) {
        String sql = "UPDATE tickets " +
                     "SET user_id = ?, available = FALSE" +
                     " WHERE id = ?";

        jdbcTemplate.update(sql, userId, ticketId);
    }
}
