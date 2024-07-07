package org.example.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    Long id; // обработать excep в случае ненахода

    @Pattern(regexp = "^\\S+$", message = "Логин не должен содержать пробелов")
    @NotBlank
    //обработать excep с одинаковым логином
    String login;

    @Length(min = 8)
    String password;

    @NotBlank
    String fio;



    // List tickets

}
