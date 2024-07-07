package org.example.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Path {

    String start;

    String finish;

    Long transporterId;

    Integer travelTime;

}
