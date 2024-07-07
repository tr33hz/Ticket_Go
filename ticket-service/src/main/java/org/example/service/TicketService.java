package org.example.service;

import org.example.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    Long save(Ticket ticket);

    Optional<Ticket> findById(Long id);

    List<Ticket> getAvailableTickets(String dateTime, String start, String finish, String transporterName, int page, int size);
}
