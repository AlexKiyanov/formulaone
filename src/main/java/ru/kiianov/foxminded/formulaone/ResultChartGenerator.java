package ru.kiianov.foxminded.formulaone;

import ru.kiianov.foxminded.formulaone.domain.Racer;
import ru.kiianov.foxminded.formulaone.parser.RaceParser;
import ru.kiianov.foxminded.formulaone.provider.RaceViewProvider;
import ru.kiianov.foxminded.formulaone.reader.FileReader;
import ru.kiianov.foxminded.formulaone.validator.FileValidator;

import java.util.List;

public class ResultChartGenerator {
    private final FileValidator validator;
    private final FileReader reader;
    private final RaceParser parser;
    private final RaceViewProvider raceViewProvider;

    public ResultChartGenerator(FileValidator validator,
                                FileReader reader,
                                RaceParser raceParser,
                                RaceViewProvider viewProvider) {
        this.validator = validator;
        this.reader = reader;
        this.parser = raceParser;
        this.raceViewProvider = viewProvider;
    }

    public String generateResultChart(String abbreviationFilePath, String startLogFilePath, String endLogFilePath) {
        validator.validate(abbreviationFilePath, startLogFilePath, endLogFilePath);

        List<String> abbreviations = reader.read(abbreviationFilePath);
        List<String> starts = reader.read(startLogFilePath);
        List<String> ends = reader.read(endLogFilePath);

        List<Racer> racers = parser.parse(ends, starts, abbreviations);
        return raceViewProvider.provideView(racers);
    }
}
