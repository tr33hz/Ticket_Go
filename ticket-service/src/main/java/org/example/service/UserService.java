package org.example.service;

import org.example.model.Ticket;
import org.example.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Long registration(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    Optional<List<Long>> getAllTickets(Long id);
}
