package io.github.mathieusoysal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import io.github.mathieusoysal.exceptions.InvalidArgumentException;

public class ArgumentManagerTest {

    @Test
    void test_getPeriod_shouldReturnCorrectPeriod() {
        String[] args = { "--period", "3" };
        ArgumentManager.setArguments(args);
        assertEquals(3, ArgumentManager.getPeriod());
    }

    @Test
    void test_getPeriod_shouldReturnCorrectPeriod_withNegativeNumber() {
        String[] args = { "--period", "-1" };
        ArgumentManager.setArguments(args);
        assertEquals(-1, ArgumentManager.getPeriod());
    }

    @Test
    void test_getPeriod_shouldReturnCorrectPeriod_withNotValidNumber() {
        String[] args = { "period", "hi", "dsqd", "574" };
        assertThrows(InvalidArgumentException.class, () -> ArgumentManager.setArguments(args));
    }

}
