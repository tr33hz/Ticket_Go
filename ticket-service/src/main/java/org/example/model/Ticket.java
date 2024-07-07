package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ticket {

    Long id;

    @NotNull
    Long pathId;

    @FutureOrPresent(message = "Дата поездки не может быть в прошлом")
    LocalDateTime dateTime;

    @NotNull
    @PositiveOrZero(message = "Место посадки не может быть меньше 0")
    Integer seatNumber;

    @Positive(message = "Цена билета не может равняться 0 или быть меньше")
    BigDecimal price;

    Boolean available;

    @JsonIgnore
    Long userId;

}
