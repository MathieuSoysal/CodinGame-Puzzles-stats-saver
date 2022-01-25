package io.github.mathieusoysal.resources;

import java.util.Map;

import com.cars.framework.secrets.DockerSecretLoadException;
import com.cars.framework.secrets.DockerSecrets;

import org.apache.logging.log4j.Logger;

import io.github.mathieusoysal.exceptions.DockerSecretVariableNotFoundException;
import io.github.mathieusoysal.exceptions.EnvironmentVariableNotFoundException;

public class ApplicationProperties {
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(ApplicationProperties.class);

    private ApplicationProperties() {
        throw new IllegalStateException("Utility class");
    }

    private static final Map<String, String> DOCKER_SECRETS = getDockerSecrets();
    private static final String MONGODB_PASSWORD = getProperty("MONGODB_PASSWORD");
    private static final String MONGODB_USERNAME = getProperty("MONGODB_USERNAME");
    private static final String MONGODB_ADDRESS = getProperty("MONGODB_ADDRESS");
    public static final String MONGODB_DATABASE = getProperty("MONGODB_DATABASE");
    public static final String MONGODB_URI = String.format("mongodb+srv://%s:%s@%s",
            MONGODB_USERNAME,
            MONGODB_PASSWORD,
            MONGODB_ADDRESS);

    private static Map<String, String> getDockerSecrets() {
        try {
            return DockerSecrets.loadFromFile("mongodb-secret");
        } catch (DockerSecretLoadException e) {
            LOGGER.info(
                    "Could not load docker secrets mongodb-secret, search if MongoDB properties are on environment variables");
            return null;
        }
    }

    private static String getProperty(final String name) {
        if (DOCKER_SECRETS != null)
            return getPropertyFromDockerSecret(name);
        return getPropertyFromEnvironmentVariable(name);
    }

    private static String getPropertyFromEnvironmentVariable(final String name) {
        var property = System.getenv(name);
        if (property == null)
            throw new DockerSecretVariableNotFoundException(name);
        return property;
    }

    private static String getPropertyFromDockerSecret(final String name) {
        String property = DOCKER_SECRETS.get(name);
        if (property == null)
            throw new EnvironmentVariableNotFoundException(name);
        return property;
    }
}
