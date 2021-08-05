package ru.kiianov.foxminded.formulaone.provider;

import org.junit.jupiter.api.Test;
import ru.kiianov.foxminded.formulaone.exception.LogFileException;
import ru.kiianov.foxminded.formulaone.validator.Validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorTest {
    private final Validator validator = new Validator();

    @Test
    void validateShouldNotThrowExceptionIfFilesCorrect() {
        assertDoesNotThrow(() -> validator.validate(
                "src/test/resources/abbreviations.txt",
                "src/test/resources/start.log",
                "src/test/resources/end.log"));
    }

    @Test
    void validateShouldThrowExceptionIfAbbreviationsDoesNotExist() {
        final Throwable exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(
                "src/test/resources/kjtrghk.txt",
                "src/test/resources/start.log",
                "src/test/resources/end.log"));
        assertEquals("file was not found. Please check path.", exception.getMessage());
    }

    @Test
    void validateShouldThrowExceptionIfStartLogDoesNotExist() {
        final Throwable exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(
                "src/test/resources/abbreviations.txt",
                "src/test/resources/kjtrghk.txt",
                "src/test/resources/end.log"));
        assertEquals("file was not found. Please check path.", exception.getMessage());
    }

    @Test
    void validateShouldThrowExceptionIfEndLogDoesNotExist() {
        final Throwable exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(
                "src/test/resources/abbreviations.txt",
                "src/test/resources/start.log",
                "src/test/resources/kjtrghk.txt"));
        assertEquals("file was not found. Please check path.", exception.getMessage());
    }

    @Test
    void validateShouldThrowExceptionIfAbbreviationsContainsDuplicates() {
        final Throwable exception = assertThrows(LogFileException.class, () -> validator.validate(
                "src/test/resources/abbreviations_bad.txt",
                "src/test/resources/start.log",
                "src/test/resources/end.log"));
        assertEquals("log contains duplicates!", exception.getMessage());
    }

    @Test
    void validateShouldThrowExceptionIfStartLogContainsDuplicates() {
        final Throwable exception = assertThrows(LogFileException.class, () -> validator.validate(
                "src/test/resources/abbreviations.txt",
                "src/test/resources/start_bad.log",
                "src/test/resources/end.log"));
        assertEquals("log contains duplicates!", exception.getMessage());
    }

    @Test
    void validateShouldThrowExceptionIfEndLogContainsDuplicates() {
        final Throwable exception = assertThrows(LogFileException.class, () -> validator.validate(
                "src/test/resources/abbreviations.txt",
                "src/test/resources/start.log",
                "src/test/resources/end_bad.log"));
        assertEquals("log contains duplicates!", exception.getMessage());
    }
}
