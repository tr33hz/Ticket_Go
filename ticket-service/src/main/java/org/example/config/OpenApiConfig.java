package org.example.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Ticket_GO",
                description = "Ticket purchase system", version = "1.0.0",
                contact = @Contact(
                        name = "Mihaylov Nikita",
                        email = "nikita.mihaylov@internet.ru",
                        url = "https://github.com/tr33hz"
                )
        )
)
public class OpenApiConfig {

}
