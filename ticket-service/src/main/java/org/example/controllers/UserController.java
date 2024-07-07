package org.example.controllers;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.exceptions.TicketAlreadyPurchasedException;
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
public class UserController {

    UserService userService;
    TicketService ticketService;

    @PostMapping
    public Long userRegistration(@Valid @RequestBody User user) {
        return userService.registration(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PatchMapping("/{ticketId}/purchase")
    public ResponseEntity<String> purchaseTicket(@PathVariable Long ticketId, @RequestParam Long userId) {
        try {
            ticketService.purchaseTicket(ticketId, userId);
            return ResponseEntity.ok("Билет успешно куплен.");
        } catch (TicketAlreadyPurchasedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/tickets/{id}")
    public Optional<List<Long>> getAllTicketsByUser(@PathVariable Long id) {
        return userService.getAllTickets(id);
    }

}
