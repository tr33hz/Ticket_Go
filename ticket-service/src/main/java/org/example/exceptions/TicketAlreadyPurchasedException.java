package org.example.exceptions;

public class TicketAlreadyPurchasedException extends RuntimeException {
    public TicketAlreadyPurchasedException(String message) {
        super(message);
    }
}
