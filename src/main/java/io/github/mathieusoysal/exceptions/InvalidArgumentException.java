package io.github.mathieusoysal.exceptions;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String message) {
        super(String.format("Error parsing arguments: %s", message));
    }

}
