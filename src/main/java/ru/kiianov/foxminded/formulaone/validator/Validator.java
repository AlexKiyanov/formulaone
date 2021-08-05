package ru.kiianov.foxminded.formulaone.validator;

import ru.kiianov.foxminded.formulaone.exception.LogFileException;
import ru.kiianov.foxminded.formulaone.reader.FileReader;
import ru.kiianov.foxminded.formulaone.reader.StreamFileReader;

import java.io.File;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator implements FileValidator {

    @Override
    public void validate(String abbreviationFilePath, String startLogFilePath, String endLogFilePath) {
        final File abbreviation = getPath(abbreviationFilePath).toFile();
        final File startLog = getPath(startLogFilePath).toFile();
        final File endLog = getPath(endLogFilePath).toFile();

        if (!abbreviation.exists() ||
                !startLog.exists() ||
                !endLog.exists()) {
            throw new IllegalArgumentException("file was not found. Please check path.");
        }

        if (isContainsDuplicates(abbreviationFilePath) ||
                isContainsDuplicates(startLogFilePath) ||
                isContainsDuplicates(endLogFilePath)) {
            throw new LogFileException("log contains duplicates!");
        }
    }

    private Path getPath(String fileName) {
        return Path.of(fileName);
    }

    private boolean isContainsDuplicates(String logName) {
        final FileReader reader = new StreamFileReader();

        final List<String> linesFromLog = reader.read(logName);

        final Set<String> linesWithoutDuplications = new HashSet<>(linesFromLog);

        return linesWithoutDuplications.size() < linesFromLog.size();
    }
}
