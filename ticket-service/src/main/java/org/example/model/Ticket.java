package org.example.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ticket {

    Path path;

    LocalDateTime dateTime;

    Integer seatNumber;

    BigDecimal price;
}
