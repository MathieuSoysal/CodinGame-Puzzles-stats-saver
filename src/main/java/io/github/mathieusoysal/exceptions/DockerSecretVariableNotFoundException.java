package io.github.mathieusoysal.exceptions;

public class DockerSecretVariableNotFoundException extends RuntimeException {
    public DockerSecretVariableNotFoundException(String message) {
        super(String.format("The %s docker secret variable was not found, check if it is well initialized.", message));
    }
}

