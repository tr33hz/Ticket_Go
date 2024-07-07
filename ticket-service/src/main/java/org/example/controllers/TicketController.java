package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.model.Ticket;
import org.example.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tickets")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name="Система билетов", description="Позволяет создавать и искать билеты")
public class TicketController {

    TicketService ticketService;

    @Operation(
            summary = "Создание билета"
    )
    @PostMapping
    public Long ticketSave(@Valid @RequestBody Ticket ticket) {
        return ticketService.save(ticket);
    }

    @Operation(
            summary = "Получение билета по id"
    )
    @GetMapping("/{id}")
    public Optional<Ticket> getTicketById(@PathVariable Long id) {
        return ticketService.findById(id);
    }

    @Operation(
            summary = "Получение списка всех доступных для покупки билетов",
            description = "Позволяет получить список билетов с возможностью пагинации и " +
                    "фильтрации по следующим атрибутам ДАТА/ВРЕМЯ, ПУНКТЫ ОТПРАВЛЕНИЯ/НАЗНАЧЕНИЯ," +
                    " НАЗВАНИЕ ПЕРЕВОЗЧИКА"
    )
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
