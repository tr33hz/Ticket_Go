package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    Long id;

    @Pattern(regexp = "^\\S+$", message = "Логин не должен содержать пробелов")
    @NotBlank
    String login;

    @Length(min = 8)
    String password;

    @NotBlank
    String fio;

    @JsonIgnore
    final List<Long> purchasedTickets = new ArrayList<>();


    public void addPurchasedTickets (Long ticketId) {
        purchasedTickets.add(ticketId);
    }
}
