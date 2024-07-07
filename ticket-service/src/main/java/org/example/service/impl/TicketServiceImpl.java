package org.example.service.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.exceptions.PathNotFoundException;
import org.example.exceptions.TicketAlreadyPurchasedException;
import org.example.exceptions.TicketNotFoundException;
import org.example.exceptions.UserNotFoundException;
import org.example.model.Ticket;
import org.example.model.User;
import org.example.repository.PathRepository;
import org.example.repository.TicketRepository;
import org.example.repository.UserRepository;
import org.example.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    TicketRepository ticketRepository;
    PathRepository pathRepository;
    UserRepository userRepository;

    @Override
    public Long save(Ticket ticket) {

        pathRepository.findById(ticket.getPathId())
                .orElseThrow(() -> new PathNotFoundException("Маршрут не найден"));


        return ticketRepository.save(ticket);
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public List<Ticket> getAvailableTickets(String dateTime, String start, String finish, String transporterName, int page, int size) {
        return ticketRepository.findAvailableTickets(dateTime, start, finish, transporterName, page, size);
    }

    @Override
    public void purchaseTicket(Long ticketId, Long userId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Билет с таким id не существует"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с таким id не существует"));

        if (!ticket.getAvailable()) {
            throw new TicketAlreadyPurchasedException("Билет уже куплен");
        }

        user.addPurchasedTickets(ticketId);
        ticketRepository.purchaseTicket(ticketId, userId);
    }
}