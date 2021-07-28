package ru.kiianov.foxminded.formulaone.validator;

import ru.kiianov.foxminded.formulaone.exception.LogFileException;
import ru.kiianov.foxminded.formulaone.provider.StreamFromFileProvider;
import ru.kiianov.foxminded.formulaone.provider.StreamProvider;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class Validator {
    public void validate(String abbreviationFileName, String startLogFileName, String endLogFileName) {

        final File abbreviation = getPath(abbreviationFileName).toFile();
        if (!abbreviation.exists()) {
            throw new IllegalArgumentException("abbreviations file was not found. Please check path.");
        }

        if (isContainsDuplicates(abbreviationFileName)) {
            throw new LogFileException("abbreviations contains duplicates!");
        }

        final File startLog = getPath(startLogFileName).toFile();
        if (!startLog.exists()) {
            throw new IllegalArgumentException("start.log file was not found. Please check path.");
        }

        if (isContainsDuplicates(startLogFileName)) {
            throw new LogFileException("start.log contains duplicates!");
        }

        final File endLog = getPath(endLogFileName).toFile();
        if (!endLog.exists()) {
            throw new IllegalArgumentException("end.log file was not found. Please check path.");
        }

        if (isContainsDuplicates(endLogFileName)) {
            throw new LogFileException("end.log contains duplicates!");
        }
    }

    private Path getPath(String fileName) {
        Path abbreviationFileName;
        try {
            if (!fileName.contains("/")) {
                abbreviationFileName = Paths.get(
                        requireNonNull(Thread.currentThread()
                                .getContextClassLoader()
                                .getResource(fileName))
                                .toURI());
            } else {
                abbreviationFileName = Path.of(fileName);
            }
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Bad filename to URI conversion.", e);
        }
        return abbreviationFileName;
    }

    private boolean isContainsDuplicates(String logName) {
        final StreamProvider streamProvider = new StreamFromFileProvider();

        final List<String> abbreviationsFromLog = streamProvider
                .provideStream(logName)
                .map(line -> line.substring(3))
                .collect(Collectors.toList());

        final Set<String> abbreviationsWithoutDuplicates = new HashSet<>(abbreviationsFromLog);

        return abbreviationsWithoutDuplicates.size() < abbreviationsFromLog.size();
    }
}
