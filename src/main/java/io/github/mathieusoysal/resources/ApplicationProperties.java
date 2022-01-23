package io.github.mathieusoysal.resources;

import io.github.mathieusoysal.exceptions.EnvironmentVariableNotFoundException;

public class ApplicationProperties {
    private ApplicationProperties() {
        throw new IllegalStateException("Utility class");
    }

    private static final String MONGODB_PASSWORD = getenv("MONGODB_PASSWORD");
    private static final String MONGODB_USERNAME = getenv("MONGODB_USERNAME");
    private static final String MONGODB_ADDRESS = getenv("MONGODB_ADDRESS");
    public static final String MONGODB_DATABASE = getenv("MONGODB_DATABASE");
    public static final String MONGODB_URI = String.format("mongodb+srv://%s:%s@%s",
            MONGODB_USERNAME,
            MONGODB_PASSWORD,
            MONGODB_ADDRESS);

    private static String getenv(final String name) {
        var value = System.getenv(name);
        if (value == null)
            throw new EnvironmentVariableNotFoundException(name);
        return System.getenv(name);
    }
}
