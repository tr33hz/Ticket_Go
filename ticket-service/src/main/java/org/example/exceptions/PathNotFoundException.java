package org.example.exceptions;

public class PathNotFoundException extends RuntimeException{
    public PathNotFoundException(String message) {
        super(message);
    }
}
