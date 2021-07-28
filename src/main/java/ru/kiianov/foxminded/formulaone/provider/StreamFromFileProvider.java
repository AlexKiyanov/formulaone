package ru.kiianov.foxminded.formulaone.provider;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class StreamFromFileProvider implements StreamProvider {

    @Override
    public Stream<String> provideStream(String fileName) {
        try {
            return Files.lines(getPath(fileName));
        } catch (IOException e) {
            throw new IllegalArgumentException(
                    "Seems that this catch is nonReachable due to getPath() throw NPE early" +
                            System.lineSeparator() +
                            "Let me know if it happens: a.kijano@gmail.com. :)", e);
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
}
