package ru.kiianov.foxminded.formulaone.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFileReader implements FileReader {

    @Override
    public List<String> read(String path) {
        try (Stream<String> fileStream = Files.lines(Path.of(path))) {
            return fileStream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException("Bad filePath. Please check it.", e);
        }
    }
}
