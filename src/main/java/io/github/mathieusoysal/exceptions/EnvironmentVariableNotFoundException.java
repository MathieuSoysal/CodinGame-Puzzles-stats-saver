package io.github.mathieusoysal.exceptions;

public class EnvironmentVariableNotFoundException extends RuntimeException {
    public EnvironmentVariableNotFoundException(String message) {
        super(String.format("The %s environment variable was not found, check if it is well initialized.", message));
    }
}
