package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.exceptions.TicketAlreadyPurchasedException;
import org.example.model.Ticket;
import org.example.model.User;
import org.example.service.TicketService;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name="Система пользователей", description="Позволяет зарегистрировать пользователя и покупать билеты")
public class UserController {

    UserService userService;
    TicketService ticketService;

    @Operation(
            summary = "Регистрация пользователя"
    )
    @PostMapping
    public Long userRegistration(@Valid @RequestBody User user) {
        return userService.registration(user);
    }

    @Operation(
            summary = "Получение пользователя по id"
    )
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @Operation(
            summary = "Получение всех пользователей",
            description = "Позволяет получить список пользователей находящихся в БД"
    )
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @Operation(
            summary = "Покупка билета для пользователя"
    )
    @PatchMapping("/purchase/{ticketId}")
    public ResponseEntity<String> purchaseTicket(@PathVariable Long ticketId, @RequestParam Long userId) {
        try {
            ticketService.purchaseTicket(ticketId, userId);
            return ResponseEntity.ok("Билет успешно куплен.");
        } catch (TicketAlreadyPurchasedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Билеты пользователя",
            description = "Позволяет получить список приобретенных билетов пользователем"
    )
    @GetMapping("/purchase/{id}")
    public Optional<List<Ticket>> getAllTicketsByUser(@PathVariable Long id) {
        return userService.getAllTickets(id);
    }

}
