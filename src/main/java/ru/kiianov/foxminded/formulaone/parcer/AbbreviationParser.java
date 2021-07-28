package ru.kiianov.foxminded.formulaone.parcer;

import ru.kiianov.foxminded.formulaone.domain.Racer;
import ru.kiianov.foxminded.formulaone.provider.StreamFromFileProvider;

import java.util.Map;
import java.util.stream.Collectors;

public class AbbreviationParser {

    public Map<String, Racer> parse(String abbreviationsFileName) {
        return new StreamFromFileProvider()
                .provideStream(abbreviationsFileName)
                .map(line -> line.split("_"))
                .collect(Collectors.toMap(
                        lineParts -> lineParts[0],
                        lineParts -> new Racer(lineParts[0], lineParts[1], lineParts[2])));
    }
}
