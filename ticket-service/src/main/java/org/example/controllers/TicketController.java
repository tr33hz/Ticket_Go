package org.example.controllers;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.model.Ticket;
import org.example.model.User;
import org.example.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tickets")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TicketController {

    TicketService ticketService;

    @PostMapping
    public Long userRegistration(@Valid @RequestBody Ticket ticket) {
        return ticketService.save(ticket);
    }

    @GetMapping("/{id}")
    public Optional<Ticket> getTicketById(@PathVariable Long id) {
        return ticketService.findById(id);
    }

    @GetMapping
    public List<Ticket> getAvailableTickets(
            @RequestParam(required = false) String dateTime,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String finish,
            @RequestParam(required = false) String transporterName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ticketService.getAvailableTickets(dateTime, start, finish, transporterName, page, size);
    }

}
