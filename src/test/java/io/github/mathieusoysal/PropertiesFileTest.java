package io.github.mathieusoysal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import io.github.mathieusoysal.resources.PropertiesFile;

public class PropertiesFileTest {

    @Test
    void dontThrowErrorOnProperties() {
        assertNotNull(PropertiesFile.MONGODB_URI);
    }

}
