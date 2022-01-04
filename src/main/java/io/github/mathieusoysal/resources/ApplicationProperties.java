package io.github.mathieusoysal.resources;

public class ApplicationProperties {
    private ApplicationProperties() {
        throw new IllegalStateException("Utility class");
    }

    private static final String MONGODB_PASSWORD = System.getenv("MONGODB_PASSWORD");
    private static final String MONGODB_USERNAME = System.getenv("MONGODB_USERNAME");
    private static final String MONGODB_ADDRESS = System.getenv("MONGODB_ADDRESS");

    public static final String MONGODB_DATABASE = System.getenv("MONGODB_DATABASE");

    public static final String MONGODB_URI = String.format("mongodb+srv://%s:%s@%s",
            MONGODB_USERNAME,
            MONGODB_PASSWORD,
            MONGODB_ADDRESS);

}
