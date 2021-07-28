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
                "abbreviations.txt",
                "start.log",
                "end.log"));
    }

    @Test
    void validateShouldThrowExceptionIfAbbreviationsDoesNotExist() {
        final Throwable exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(
                "/Users/aleksei/kjtrghk.txt",
                "start.log",
                "end.log"));
        assertEquals("abbreviations file was not found. Please check path.", exception.getMessage());
    }

    @Test
    void validateShouldThrowExceptionIfAbbreviationsContainsDuplicates() {
        final Throwable exception = assertThrows(LogFileException.class, () -> validator.validate(
                "abbreviations_bad.txt",
                "start.log",
                "end.log"));
        assertEquals("abbreviations contains duplicates!", exception.getMessage());
    }

    @Test
    void validateShouldThrowExceptionIfStartLogDoesNotExist() {
        final Throwable exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(
                "abbreviations.txt",
                "/Users/aleksei/kjtrghk.txt",
                "end.log"));
        assertEquals("start.log file was not found. Please check path.", exception.getMessage());
    }

    @Test
    void validateShouldThrowExceptionIfStartLogContainsDuplicates() {
        final Throwable exception = assertThrows(LogFileException.class, () -> validator.validate(
                "abbreviations.txt",
                "start_bad.log",
                "end.log"));
        assertEquals("start.log contains duplicates!", exception.getMessage());
    }

    @Test
    void validateShouldThrowExceptionIfEndLogDoesNotExist() {
        final Throwable exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(
                "abbreviations.txt",
                "start.log",
                "/Users/aleksei/kjtrghk.txt"));
        assertEquals("end.log file was not found. Please check path.", exception.getMessage());
    }

    @Test
    void validateShouldThrowExceptionIfEndLogContainsDuplicates() {
        final Throwable exception = assertThrows(LogFileException.class, () -> validator.validate(
                "abbreviations.txt",
                "start.log",
                "end_bad.log"));
        assertEquals("end.log contains duplicates!", exception.getMessage());
    }
}
